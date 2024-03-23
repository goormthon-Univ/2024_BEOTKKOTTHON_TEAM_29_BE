package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.entity.ColorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryUpdateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private Long user_id;
        private String title;
        private ColorType color;
    }

    // Response
    public static SimpleResponse from(Category category) {
        return new SimpleResponse(category.getTitle(), category.getColor(), category.getTomato());
    }
}
