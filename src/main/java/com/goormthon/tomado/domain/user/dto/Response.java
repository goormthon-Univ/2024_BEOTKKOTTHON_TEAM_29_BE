package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {

    private String login_id;
    private String password;
    private String nickname;
    private String character_url;
    private int tomato;

    private Response(String login_id, String password, String nickname, String character_url, int tomato) {
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
        this.character_url = character_url;
        this.tomato = tomato;
    }

    public static Response from(User user) {
        return new Response(user.getLoginId(), user.getPassword(), user.getNickname(), user.getCharacterUrl(), user.getTomato());
    }

}
