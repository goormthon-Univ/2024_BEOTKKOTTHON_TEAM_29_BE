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
    USER_INFO_CHANGE_SUCCESS(OK, "회원 정보 수정 성공"),
    USER_INFO_FIND_SUCCESS(OK, "회원 조회 성공"),
    LOGIN_ID_VALIDATE_SUCCESS(OK, "아이디 중복 체크 성공"),
    USER_WITHDRAW_SUCCESS(OK, "회원 탈퇴 성공"),

    CATEGORY_SAVE_SUCCESS(CREATED, "카테고리 생성 성공"),
    CATEGORY_LIST_FETCH_SUCCESS(OK, "카테고리 조회 성공"),
    CATEGORY_UPDATE_SUCCESS(OK, "카테고리 수정 성공"),
    CATEGORY_DELETE_SUCCESS(OK, "카테고리 삭제 성공"),

    MEMO_CREATE_SUCCESS(CREATED, "메모 작성 성공"),
    MEMO_DELETE_SUCCESS(OK, "메모 삭제 성공"),
    MEMO_LIST_FETCH_SUCCESS(OK, "메모 조회 성공"),

    TASK_SAVE_SUCCESS(CREATED, "Task 생성 성공"),
    TOMA_SAVE_SUCCESS(OK, "토마 적립 성공"),
    TASK_TOMA_LIST_FETCH_SUCCESS(OK, "월별 토마 개수 조회 성공"),

    TOMADO_FETCH_SUCCESS(OK, "캐릭터 조회 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}
