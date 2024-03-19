package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.entity.ColorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryCreateDto {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String title;

        private Response(String title) {
            this.title = title;
        }
    }

    public static Response from(Category category) {
        return new Response(category.getTitle());
    }
}