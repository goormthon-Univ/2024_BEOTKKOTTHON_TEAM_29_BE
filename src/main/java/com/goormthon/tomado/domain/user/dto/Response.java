package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Response {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detailed {
        private String login_id;
        private String password;
        private String nickname;
        private String character_url;
        private int tomato;

        private Detailed(String login_id, String password, String nickname, String character_url, int tomato) {
            this.login_id = login_id;
            this.password = password;
            this.nickname = nickname;
            this.character_url = character_url;
            this.tomato = tomato;
        }

        public static Detailed from(User user) {
            return new Detailed(user.getLoginId(), user.getPassword(), user.getNickname(), user.getCharacterUrl(), user.getTomato());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Simple {

        private Long user_id;

        private Simple(Long user_id) {
            this.user_id = user_id;
        }

        public static Simple from(Long user_id) {
            return new Simple(user_id);
        }
    }


}
