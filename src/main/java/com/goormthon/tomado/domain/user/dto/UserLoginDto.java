package com.goormthon.tomado.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserLoginDto {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private Long user_id;

        public Response(Long user_id) {
            this.user_id = user_id;
        }

    }

    public static Response from(Long user_id) {
        return new Response(user_id);
    }

}
