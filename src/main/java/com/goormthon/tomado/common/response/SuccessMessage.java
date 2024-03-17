package com.goormthon.tomado.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    USER_SIGNUP_SUCCESS(CREATED, "회원 가입 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}