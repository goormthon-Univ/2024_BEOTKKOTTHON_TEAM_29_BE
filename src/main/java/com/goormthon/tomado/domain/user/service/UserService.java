package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.user.dto.UserLoginDto;
import com.goormthon.tomado.domain.user.dto.UserSignUpDto;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.goormthon.tomado.common.response.ErrorMessage.USER_LOGIN_ID_NOT_EXIST;
import static com.goormthon.tomado.common.response.ErrorMessage.USER_PASSWORD_NOT_EXIST;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public ApiResponse<UserSignUpDto.Response> signUp(UserSignUpDto.Request request) {

        User user = new User(request.getLogin_id(), request.getPassword(), request.getNickname());
        userRepository.save(user);
        return ApiResponse.success(USER_SIGNUP_SUCCESS, UserSignUpDto.from(user));

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

}
