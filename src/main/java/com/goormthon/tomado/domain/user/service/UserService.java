package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.TomadoApplication;
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

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    final private TomadoRepository tomadoRepository;
    final private UserTomadoRepository userTomadoRepository;

    @Transactional
    public ApiResponse<SimpleResponse> signUp(SignUpRequest request) {

        User user = new User(request.getLogin_id(), passwordEncoder.encode(request.getPassword()), request.getNickname());
        try {
            User userRegistered = userRepository.save(user);

            // 기본 토마두 지급
            UserTomado userTomado = userTomadoRepository.save(new UserTomado(userRegistered, tomadoRepository.findById(1L)
                    .orElseThrow(() -> new NotFoundException(TOMADO_NOT_EXIST))));
            userRegistered.getUserTomadoList().add(userTomado);
            userRepository.save(userRegistered);

        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException(USER_LOGIN_ID_VALIDATE);
        }
        return ApiResponse.success(USER_SIGNUP_SUCCESS, new SimpleResponse(user.getId()));

    }

    public ApiResponse<Boolean> validateLoginId(String loginId) {
        return ApiResponse.success(LOGIN_ID_VALIDATE_SUCCESS, userRepository.findByLoginId(loginId).isPresent());
    }

    @Transactional
    public ApiResponse<SimpleResponse> login(LoginRequest request) {

        User user = userRepository.findByLoginId(request.getLogin_id())
                .orElseThrow(() -> new NotFoundException(USER_LOGIN_ID_NOT_EXIST));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ApiResponse.success(USER_LOGIN_SUCCESS, new SimpleResponse(user.getId()));
        } else {
            throw new NotFoundException(USER_PASSWORD_NOT_EXIST);
        }
    }

    @Transactional
    public ApiResponse<SimpleResponse> change(Long user_id, ChangeRequest request) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            User userChanged = userRepository.save(user.change(request));
            return ApiResponse.success(USER_INFO_CHANGE_SUCCESS, new SimpleResponse(userChanged.getId()));
        } catch (DataIntegrityViolationException exception) {
            throw new BadRequestException(USER_LOGIN_ID_VALIDATE);
        }

    }

    @Transactional(readOnly = true)
    public ApiResponse<Response> findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return ApiResponse.success(USER_INFO_FIND_SUCCESS, Response.from(user));
    }

    @Transactional
    public ApiResponse withdraw(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        userRepository.delete(user);
        return ApiResponse.success(USER_WITHDRAW_SUCCESS);
    }

    public ApiResponse<List<BookResponse.Simple>> getBook(Long userId) {
        // 회원 정보 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));

        // 회원이 보유한 토마두 가져오기
        ArrayList<BookResponse.Simple> simpleResponseList = new ArrayList<BookResponse.Simple>();
        for (UserTomado userTomado : user.getUserTomadoList()) {
            simpleResponseList.add(BookResponse.Simple.from(userTomado));
        }

        return ApiResponse.success(BOOK_FETCH_SUCCESS, simpleResponseList);
    }

    public ApiResponse<BookResponse.Detailed> getTomadoInfoOfBook(Long userId, Long tomadoId) {
        // 회원 정보 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));

        // 토마두 정보 확인
        Tomado tomado = tomadoRepository.findById(tomadoId).orElseThrow(() -> new NotFoundException(TOMADO_NOT_EXIST));

        // 유저가 보유한 토마두 정보인지 확인
        UserTomado userTomado = userTomadoRepository.findByUserIdAndTomadoId(user.getId(), tomado.getId())
                .orElseThrow(() -> new BadRequestException(USER_NOT_HAVE_TOMADO));

        return ApiResponse.success(TOMADO_FETCH_SUCCESS, BookResponse.Detailed.from(userTomado));
    }

}
