package com.goormthon.tomado.domain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryListByDateDto {
    private List<SimpleResponse> categoryList;

    private CategoryListByDateDto(List<SimpleResponse> simpleResponseList) {
        this.categoryList = simpleResponseList;
    }

    public static CategoryListByDateDto fromCategoriesByDate(List<SimpleResponse> categoryList) {
        return new CategoryListByDateDto(categoryList);
    }
}
