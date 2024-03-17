package com.goormthon.tomado.domain.user.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.user.dto.UserSignUpDto;
import com.goormthon.tomado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @PostMapping("users/signup")
    public ApiResponse<UserSignUpDto.Response> signUp(@RequestBody UserSignUpDto.Request request) {
        return userService.signUp(request);
    }
}
