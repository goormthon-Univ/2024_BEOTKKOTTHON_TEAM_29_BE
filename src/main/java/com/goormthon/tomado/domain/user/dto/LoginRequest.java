package com.goormthon.tomado.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {

    private String login_id;
    private String password;

    public LoginRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }

}
