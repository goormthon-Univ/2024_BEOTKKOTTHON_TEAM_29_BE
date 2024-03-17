package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserSignUpDto {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private String login_id;
        private String password;
        private String nickname;

        private Response(String login_id, String password, String nickname) {
            this.login_id = login_id;
            this.password = password;
            this.nickname = nickname;
        }

    }
    public static Response from(User user) {
        return new Response(user.getLoginId(), user.getPassword(), user.getNickname());
    }

}
