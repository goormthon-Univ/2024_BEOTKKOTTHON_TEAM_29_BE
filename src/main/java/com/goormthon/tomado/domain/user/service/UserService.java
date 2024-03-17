package com.goormthon.tomado.domain.user.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.user.dto.UserSignUpDto;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
