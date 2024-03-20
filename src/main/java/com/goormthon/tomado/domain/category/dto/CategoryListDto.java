package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryListDto {

    private List<Response> categoryList;

    public CategoryListDto(List<Response> categoryList) {
        this.categoryList = categoryList;
    }

    public static CategoryListDto from(List<Category> categoryList) {
        List<Response> categoryDtoList = categoryList.stream()
                .filter(category -> !category.isDeleted())
                .map(category -> new Response(category.getId(), category.getTitle(), category.getColor(), category.getTomato()))
                .collect(Collectors.toList());

        return new CategoryListDto(categoryDtoList);
    }
}
