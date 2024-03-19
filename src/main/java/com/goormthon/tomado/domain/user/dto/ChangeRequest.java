package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeRequest {

    private String login_id;
    private String password;
    private String nickname;
    private String character_url;

    private ChangeRequest(String login_id, String password, String nickname, String character_url) {
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
        this.character_url = character_url;
    }

    public ChangeRequest from(User user) {
        return new ChangeRequest(user.getLoginId(), user.getPassword(), user.getNickname(), user.getCharacterUrl());
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
