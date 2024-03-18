package com.goormthon.tomado.domain.user.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.user.dto.*;
import com.goormthon.tomado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @PostMapping("users/signup")
    public ApiResponse<UserSignUpDto.Response> signUp(@RequestBody UserSignUpDto.Request request) {
        return userService.signUp(request);
    }

    @PostMapping("/users/signup/exists")
    public ApiResponse<Boolean> validateLoginId(@RequestBody LoginIdCheckDto loginIdCheckDto) {
        return userService.validateLoginId(loginIdCheckDto.getLogin_id());
    }

    @PostMapping("users/login")
    public ApiResponse<UserLoginDto.Response> login(@RequestBody UserLoginDto.Request request) {
        return userService.login(request);
    }

    @PutMapping("users/{user_id}")
    public ApiResponse<UserChangeDto.Response> change(@PathVariable Long user_id, @RequestBody UserChangeDto.Request request) {
        return userService.change(user_id, request);
    }

    @GetMapping("users/{user_id}")
    public ApiResponse<UserInfoDto> findById(@PathVariable Long user_id) {
        return userService.findById(user_id);
    }

}
