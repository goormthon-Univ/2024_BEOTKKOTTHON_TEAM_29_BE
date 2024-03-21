package com.goormthon.tomado.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    USER_LOGIN_ID_NOT_EXIST(NOT_FOUND, "존재하지 않는 아이디"),
    USER_PASSWORD_NOT_EXIST(NOT_FOUND, "존재하지 않는 비밀번호"),
    USER_NOT_EXIST(NOT_FOUND, "존재하지 않는 회원"),
    USER_LOGIN_ID_VALIDATE(BAD_REQUEST, "아이디 중복"),

    CATEGORY_TITLE_EXISTS(BAD_REQUEST, "이미 존재하는 카테고리입니다."),
    CATEGORY_NOT_EXIST(NOT_FOUND, "존재하지 않는 카테고리"),
      
    MEMO_NOT_EXIST(NOT_FOUND, "존재하지 않는 메모"),
    USER_NOT_HAVE_MEMO(BAD_REQUEST, "회원이 가진 메모가 아닙니다"),
  
    TASK_NOT_EXIST(NOT_FOUND, "존재하지 않는 Task"),

    TOMADO_NOT_EXIST(NOT_FOUND, "존재하지 않는 토마두 캐릭터"),
    USER_TOMATO_NOT_ENOUGH(BAD_REQUEST, "회원의 토마량 보유 부족"),
    USER_TOMADO_ALREADY_EXIST(BAD_REQUEST, "이미 보유한 토마두"),
    USER_NOT_HAVE_TOMADO(BAD_REQUEST, "회원이 가진 토마두가 아닙니다"),
  
    CLUB_NOT_EXIST(NOT_FOUND, "존재하지 않는 클럽"),
    USER_NOT_CLUB_MEMBER(BAD_REQUEST, "회원이 클럽 멤버가 아님"),
    CLUB_MEMBER_NUMBER_FULL(BAD_REQUEST, "클럽 만원"),
    USER_CLUB_ALREADY_EXIST(BAD_REQUEST, "이미 가입한 클럽")
   
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}