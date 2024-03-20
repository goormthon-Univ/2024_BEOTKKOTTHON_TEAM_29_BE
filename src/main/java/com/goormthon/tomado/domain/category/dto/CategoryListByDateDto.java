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
public class CategoryListByDateDto {
    private List<SimpleResponse> categoryList;

    private CategoryListByDateDto(List<SimpleResponse> simpleResponseList) {
        this.categoryList = simpleResponseList;
    }

    public static CategoryListByDateDto fromCategoriesByDate(List<Task> taskList) {
        Map<Category, Integer> categoryMap = new HashMap<>();
        for (Task task : taskList) {
            Category category = task.getCategory();
            int tomatoCount = task.getTomato();
            categoryMap.put(category, categoryMap.getOrDefault(category, 0) + tomatoCount);
        }

        List<SimpleResponse> simpleResponseList = categoryMap.entrySet().stream()
                .map(entry -> new SimpleResponse(entry.getKey().getTitle(), entry.getKey().getColor(), entry.getValue()))
                .collect(Collectors.toList());

        return new CategoryListByDateDto(simpleResponseList);
    }
}
