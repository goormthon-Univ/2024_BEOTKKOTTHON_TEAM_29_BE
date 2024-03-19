package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.user.dto.*;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    @Transactional
    public ApiResponse<SimpleResponse> signUp(SignUpRequest request) {

        User user = new User(request.getLogin_id(), passwordEncoder.encode(request.getPassword()), request.getNickname());
        try {
            userRepository.save(user);
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

}
