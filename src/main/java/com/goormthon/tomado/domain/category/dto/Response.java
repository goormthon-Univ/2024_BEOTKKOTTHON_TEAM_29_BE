package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.ColorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {

    private Long category_id;
    private String title;
    private ColorType color;
    private int tomato;

    public Response(Long category_id, String title, ColorType color, int tomato) {
        this.category_id = category_id;
        this.title = title;
        this.color = color;
        this.tomato = tomato;
    }
}
