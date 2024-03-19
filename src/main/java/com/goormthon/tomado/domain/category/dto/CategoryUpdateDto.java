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

        private String login_id;
        private String title;
        private ColorType color;

        private Request(String login_id, String title, ColorType color) {
            this.login_id = login_id;
            this.title = title;
            this.color = color;
        }
    }

    // Response
    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getTitle(), category.getColor(), category.getTomato());
    }
}
