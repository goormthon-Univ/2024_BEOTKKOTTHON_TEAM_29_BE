package com.goormthon.tomado.domain.user.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.user.dto.*;
import com.goormthon.tomado.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @Operation(summary = "회원 가입")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호 / nickname : 별명", required = true)
    @PostMapping("users/signup")
    public ApiResponse<Response.Simple> signUp(@RequestBody SignUpDto.Request request) {
        return userService.signUp(request);
    }

    @PostMapping("/users/signup/exists")
    public ApiResponse<Boolean> validateLoginId(@RequestBody SignUpDto.Check request) {
        return userService.validateLoginId(request.getLogin_id());
    }

    @Operation(summary = "로그인")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호", required = true)
    @PostMapping("users/login")
    public ApiResponse<Response.Simple> login(@RequestBody LoginDto.Request request) {
        return userService.login(request);
    }

    @Operation(summary = "회원 정보 수정")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "login_id : 아이디 / password : 비밀번호 / nickname : 별명 / character_url : 캐릭터 이미지 주소")
    @PutMapping("users/{user_id}")
    public ApiResponse<Response.Simple> change(@PathVariable Long user_id, @RequestBody ChangeRequest request) {
        return userService.change(user_id, request);
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping("users/{user_id}")
    public ApiResponse<Response.Detailed> getUserInfo(@PathVariable Long user_id) {
        return userService.getUserInfo(user_id);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/users/{user_id}")
    public ApiResponse withdraw(@PathVariable Long user_id) {
        return userService.withdraw(user_id);
    }

    @Operation(summary = "토마 도감 보기 - 얻은 토마두 전체 보기")
    @GetMapping("/book/users/{user_id}/tomados")
    public ApiResponse<List<BookResponse.Simple>> getBook(@PathVariable(name = "user_id") Long user_id) {
        return userService.getBook(user_id);
    }

    @Operation(summary = "토마 도감 보기 - 얻은 토마두 개별 보기")
    @GetMapping("/book")
    public ApiResponse<BookResponse.Detailed> getTomadoInfoOfBook(@RequestParam(name = "user") Long user_id, @RequestParam(name = "tomado") Long tomado_id) {
        return userService.getTomadoInfoOfBook(user_id, tomado_id);
    }

}
