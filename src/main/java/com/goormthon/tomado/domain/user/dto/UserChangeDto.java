package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserChangeDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private String login_id;
        private String password;
        private String nickname;
        private String character_url;

        public Request(String login_id, String password, String nickname, String character_url) {
            this.login_id = login_id;
            this.password = password;
            this.nickname = nickname;
            this.character_url = character_url;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private String login_id;
        private String password;
        private String nickname;
        private String character_url;

        public Response(String login_id, String password, String nickname, String character_url) {
            this.login_id = login_id;
            this.password = password;
            this.nickname = nickname;
            this.character_url = character_url;
        }

    }

    public static Response from(User user) {
        return new Response(user.getLoginId(), user.getPassword(), user.getNickname(), user.getCharacterUrl());
    }

}
