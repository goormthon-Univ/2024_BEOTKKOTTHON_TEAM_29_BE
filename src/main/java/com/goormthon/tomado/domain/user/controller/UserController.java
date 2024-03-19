package com.goormthon.tomado.domain.user.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.user.dto.*;
import com.goormthon.tomado.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @Operation(summary = "회원 가입")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호 / nickname : 별명", required = true
            ,content = @Content(schema = @Schema(implementation = SignUpRequest.class)))
    @PostMapping("users/signup")
    public ApiResponse<SimpleResponse> signUp(@RequestBody SignUpRequest request) {
        return userService.signUp(request);
    }

    @PostMapping("/users/signup/exists")
    public ApiResponse<Boolean> validateLoginId(@RequestBody LoginIdCheckDto loginIdCheckDto) {
        return userService.validateLoginId(loginIdCheckDto.getLogin_id());
    }

    @Operation(summary = "로그인")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호", required = true
            ,content = @Content(schema = @Schema(implementation = LoginRequest.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponses
    @PostMapping("users/login")
    public ApiResponse<SimpleResponse> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @Operation(summary = "회원 정보 수정")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호 / nickname : 별명 / character_url : 캐릭터 이미지 주소"
            , content = @Content(schema = @Schema(implementation = ChangeRequest.class)))
    @PutMapping("users/{user_id}")
    public ApiResponse<SimpleResponse> change(@PathVariable Long user_id, @RequestBody ChangeRequest request) {
        return userService.change(user_id, request);
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping("users/{user_id}")
    public ApiResponse<Response> findById(@PathVariable Long user_id) {
        return userService.findById(user_id);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/users/{user_id}")
    public ApiResponse withdraw(@PathVariable Long user_id) {
        return userService.withdraw(user_id);
    }

}
