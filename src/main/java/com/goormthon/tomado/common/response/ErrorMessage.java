package com.goormthon.tomado.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    USER_LOGIN_ID_NOT_EXIST(NOT_FOUND, "존재하지 않는 아이디"),
    USER_PASSWORD_NOT_EXIST(NOT_FOUND, "존재하지 않는 비밀번호"),
    USER_NOT_EXIST(NOT_FOUND, "존재하지 않는 회원"),
    USER_LOGIN_ID_VALIDATE(BAD_REQUEST, "아이디 중복"),

    MEMO_NOT_EXIST(NOT_FOUND, "존재하지 않는 메모"),
    USER_NOT_HAVE_MEMO(BAD_REQUEST, "회원이 가진 메모가 아닙니다")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}