package com.goormthon.tomado.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    USER_LOGIN_SUCCESS(OK, "로그인 성공"),
    USER_SIGNUP_SUCCESS(CREATED, "회원 가입 성공"),
    USER_INFO_CHANGE_SUCCESS(OK, "회원 정보 수정 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}