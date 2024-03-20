package com.goormthon.tomado.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimpleResponse {

    private Long user_id;

    public SimpleResponse(Long user_id) {
        this.user_id = user_id;
    }

}
