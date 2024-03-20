package com.goormthon.tomado.domain.category.dto;

import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.task.entity.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryListDto {

    private List<CategoryDto> categoryList;

    private CategoryListDto(List<CategoryDto> categoryDtoList) {
        this.categoryList = categoryDtoList;
    }

    public static CategoryListDto fromAll(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .filter(category -> !category.isDeleted())
                .map(category -> new CategoryDto(category.getTitle(), category.getColor(), category.getTomato()))
                .collect(Collectors.toList());

        return new CategoryListDto(categoryDtoList);
    }

    public static CategoryListDto fromCategoriesByDate(List<Task> taskList) {
        Map<Category, Integer> categoryMap = new HashMap<>();
        for (Task task : taskList) {
            Category category = task.getCategory();
            int tomatoCount = task.getTomato();
            categoryMap.put(category, categoryMap.getOrDefault(category, 0) + tomatoCount);
        }

        List<CategoryDto> categoryDtoList = categoryMap.entrySet().stream()
                .map(entry -> new CategoryDto(entry.getKey().getTitle(), entry.getKey().getColor(), entry.getValue()))
                .collect(Collectors.toList());

        return new CategoryListDto(categoryDtoList);
    }
}
