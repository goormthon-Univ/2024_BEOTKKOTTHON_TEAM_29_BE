package com.goormthon.tomado.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    private String login_id;
    private String password;
    private String nickname;

    public SignUpRequest(String login_id, String password, String nickname) {
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
    }

}
