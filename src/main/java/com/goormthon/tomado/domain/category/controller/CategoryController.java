package com.goormthon.tomado.domain.category.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.category.dto.CategoryCreateDto;
import com.goormthon.tomado.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    final private CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryCreateDto.Response> createCategory(@RequestBody CategoryCreateDto.Request request) {
        return categoryService.createCategory(request);
    }
}
