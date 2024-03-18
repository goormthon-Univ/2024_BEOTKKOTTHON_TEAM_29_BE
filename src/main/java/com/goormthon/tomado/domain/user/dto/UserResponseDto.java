package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {
    
    private Long user_id;
    private String login_id;
    private String password;
    private String nickname;
    private String character_url;
    private Long tomato;

    private UserResponseDto(Long user_id, String login_id, String password, String nickname, String character_url, Long tomato) {
        this.user_id = user_id;
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
        this.character_url = character_url;
        this.tomato = tomato;
    }

    public UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), getLogin_id(), getPassword(), getNickname(), getCharacter_url(), getTomato());
    }
    
}
