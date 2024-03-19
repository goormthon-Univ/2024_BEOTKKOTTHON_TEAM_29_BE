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

    private List<CategoryDto> categoryList;

    private CategoryListDto(List<CategoryDto> categoryDtoList) {
        this.categoryList = categoryDtoList;
    }

    public static CategoryListDto from(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .filter(category -> !category.isDeleted())
                .map(category -> new CategoryDto(category.getTitle(), category.getColor(), category.getTomato()))
                .collect(Collectors.toList());

        return new CategoryListDto(categoryDtoList);
    }
}
