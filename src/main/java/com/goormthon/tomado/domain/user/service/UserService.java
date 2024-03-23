package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.tomado.entity.Tomado;
import com.goormthon.tomado.domain.tomado.repository.TomadoRepository;
import com.goormthon.tomado.domain.user.dto.*;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.entity.UserTomado;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import com.goormthon.tomado.domain.user.repository.UserTomadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    final private TomadoRepository tomadoRepository;
    final private UserTomadoRepository userTomadoRepository;

    public ApiResponse<Response.Simple> signUp(SignUpDto.Request request) {
        User user = new User(request.getLogin_id(), passwordEncoder.encode(request.getPassword()), request.getNickname());
        User userRegistered = validateLoginInfo(user);
        giveDefaultTomado(userRegistered);
        return ApiResponse.success(USER_SIGNUP_SUCCESS, Response.Simple.from(user.getId()));
    }

    private User validateLoginInfo(User user) {
        try {
            return userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException(USER_LOGIN_ID_VALIDATE);
        }
    }

    private void giveDefaultTomado(User user) {
        // 기본 토마두 지급
        UserTomado userTomado = userTomadoRepository.save(new UserTomado(user, getTomado(1L)));
        user.getUserTomadoList().add(userTomado);
        userRepository.save(user);
    }


    public ApiResponse<Boolean> validateLoginId(String loginId) {
        return ApiResponse.success(LOGIN_ID_VALIDATE_SUCCESS, userRepository.findByLoginId(loginId).isPresent());
    }

    public ApiResponse<Response.Simple> login(LoginDto.Request request) {
        User user = userRepository.findByLoginId(request.getLogin_id()).orElseThrow(() -> new NotFoundException(USER_LOGIN_ID_NOT_EXIST));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ApiResponse.success(USER_LOGIN_SUCCESS, Response.Simple.from(user.getId()));
        } else {
            throw new NotFoundException(USER_PASSWORD_NOT_EXIST);
        }
    }

    public ApiResponse<Response.Simple> change(Long user_id, ChangeRequest request) {
        User user = getUser(user_id);

        if (request.getPassword().isEmpty()) {
            request.setPassword(request.getPassword());
        } else {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User userChanged = validateLoginInfo(user.change(request));
        return ApiResponse.success(USER_INFO_CHANGE_SUCCESS, Response.Simple.from(userChanged.getId()));
    }

    @Transactional(readOnly = true)
    public ApiResponse<Response.Detailed> getUserInfo(Long userId) {
        User user = getUser(userId);
        return ApiResponse.success(USER_INFO_FIND_SUCCESS, Response.Detailed.from(user));
    }

    public ApiResponse withdraw(Long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        return ApiResponse.success(USER_WITHDRAW_SUCCESS);
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<BookResponse.Simple>> getBook(Long userId) {
        // 회원 정보 확인
        User user = getUser(userId);

        // 회원이 보유한 토마두 가져오기
        ArrayList<BookResponse.Simple> simpleResponseList = new ArrayList<BookResponse.Simple>();
        for (UserTomado userTomado : user.getUserTomadoList()) {
            simpleResponseList.add(BookResponse.Simple.from(userTomado));
        }

        return ApiResponse.success(BOOK_FETCH_SUCCESS, simpleResponseList);
    }

    @Transactional(readOnly = true)
    public ApiResponse<BookResponse.Detailed> getTomadoInfoOfBook(Long userId, Long tomadoId) {
        // 회원 정보 확인
        User user = getUser(userId);

        // 토마두 정보 확인
        Tomado tomado = getTomado(tomadoId);

        // 유저가 보유한 토마두 정보인지 확인
        UserTomado userTomado = userTomadoRepository.findByUserIdAndTomadoId(user.getId(), tomado.getId())
                .orElseThrow(() -> new BadRequestException(USER_NOT_HAVE_TOMADO));

        return ApiResponse.success(TOMADO_FETCH_SUCCESS, BookResponse.Detailed.from(userTomado));
    }

    private User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return user;
    }

    private Tomado getTomado(long id) {
        return tomadoRepository.findById(id).orElseThrow(() -> new NotFoundException(TOMADO_NOT_EXIST));
    }

}

