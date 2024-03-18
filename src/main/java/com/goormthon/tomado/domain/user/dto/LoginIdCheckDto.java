package com.goormthon.tomado.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginIdCheckDto {

    String login_id;

    public LoginIdCheckDto(String login_id) {
        this.login_id = login_id;
    }

}
