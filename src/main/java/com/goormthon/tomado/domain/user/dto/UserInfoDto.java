package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoDto {

    private String login_id;
    private String password;
    private String nickname;
    private String character_url;
    private int tomato;

    private UserInfoDto(String login_id, String password, String nickname, String character_url, int tomato) {
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
        this.character_url = character_url;
        this.tomato = tomato;
    }

    public static UserInfoDto from(User user) {
        return new UserInfoDto(user.getLoginId(), user.getPassword(), user.getNickname(), user.getCharacterUrl(), user.getTomato());
    }

}
