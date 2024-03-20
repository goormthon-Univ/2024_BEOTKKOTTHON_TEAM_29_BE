package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.ColorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleResponse {

    private String title;
    private ColorType color;
    private int tomato;

    public SimpleResponse(String title, ColorType color, int tomato) {
        this.title = title;
        this.color = color;
        this.tomato = tomato;
    }
}
