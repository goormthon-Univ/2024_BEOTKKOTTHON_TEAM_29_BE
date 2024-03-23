package com.goormthon.tomado.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LoginDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String login_id;
        private String password;

        public Request(String login_id, String password) {
            this.login_id = login_id;
            this.password = password;
        }
    }

}
