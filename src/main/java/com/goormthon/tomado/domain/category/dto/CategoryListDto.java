package com.goormthon.tomado.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryListDto {

    private List<Response> categoryList;

    public CategoryListDto(List<Response> categoryList) {
        this.categoryList = categoryList;
    }

    public static CategoryListDto from(List<Response> categoryList) {
        return new CategoryListDto(categoryList);
    }
}
