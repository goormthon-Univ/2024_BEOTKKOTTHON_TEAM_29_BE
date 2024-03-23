package com.goormthon.tomado.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SignUpDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private String login_id;
        private String password;
        private String nickname;

        public Request(String login_id, String password, String nickname) {
            this.login_id = login_id;
            this.password = password;
            this.nickname = nickname;
        }
    }




}
