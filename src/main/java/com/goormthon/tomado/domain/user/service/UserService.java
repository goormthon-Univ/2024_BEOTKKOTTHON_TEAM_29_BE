package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.user.dto.UserChangeDto;
import com.goormthon.tomado.domain.user.dto.UserInfoDto;
import com.goormthon.tomado.domain.user.dto.UserLoginDto;
import com.goormthon.tomado.domain.user.dto.UserSignUpDto;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public ApiResponse<UserSignUpDto.Response> signUp(UserSignUpDto.Request request) {

        User user = new User(request.getLogin_id(), request.getPassword(), request.getNickname());
        try {
            userRepository.save(user);
        } catch (RuntimeException exception) {
            throw new BadRequestException(USER_LOGIN_ID_VALIDATE);
        }
        return ApiResponse.success(USER_SIGNUP_SUCCESS, UserSignUpDto.from(user));

    }

    public ApiResponse<Boolean> validateLoginId(String loginId) {
        return ApiResponse.success(LOGIN_ID_VALIDATE_SUCCESS, userRepository.findByLoginId(loginId).isPresent());
    }

    public ApiResponse<UserLoginDto.Response> login(UserLoginDto.Request request) {

        User user = userRepository.findByLoginId(request.getLogin_id())
                .orElseThrow(() -> new NotFoundException(USER_LOGIN_ID_NOT_EXIST));

        if (user.getPassword().equals(request.getPassword())) {
            return ApiResponse.success(USER_LOGIN_SUCCESS, UserLoginDto.from(user.getId()));
        } else {
            throw new NotFoundException(USER_PASSWORD_NOT_EXIST);
        }
    }

    public ApiResponse<UserChangeDto.Response> change(Long user_id, UserChangeDto.Request request) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        try {
            User userChanged = userRepository.save(user.change(request));
            return ApiResponse.success(USER_INFO_CHANGE_SUCCESS, UserChangeDto.from(userChanged));
        } catch (RuntimeException exception) {
            throw new BadRequestException(USER_LOGIN_ID_VALIDATE);
        }

    }

    public ApiResponse<UserInfoDto> findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return ApiResponse.success(USER_INFO_FIND_SUCCESS, UserInfoDto.from(user));
    }

    public ApiResponse withdraw(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        userRepository.delete(user);
        return ApiResponse.success(USER_WITHDRAW_SUCCESS);
    }

}
