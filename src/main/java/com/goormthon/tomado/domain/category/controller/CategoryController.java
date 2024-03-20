package com.goormthon.tomado.domain.category.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.category.dto.*;
import com.goormthon.tomado.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    final private CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryCreateDto.Response> createCategory(@RequestBody CategoryCreateDto.Request request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{user_id}")
    public ApiResponse<CategoryListDto> findAllCategories(@PathVariable(name = "user_id") Long user_id) {
        return categoryService.findAllCategories(user_id);
    }

    @GetMapping
    public ApiResponse<CategoryListByDateDto> findCategoriesByDate(
            @RequestParam("user") Long user_id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selected_date) {
        return categoryService.findCategoriesByDate(user_id, selected_date);
    }

    @PutMapping("/{category_id}")
    public ApiResponse<SimpleResponse> updateCategory(@PathVariable(name = "category_id") Long category_id, @RequestBody CategoryUpdateDto.Request request) {
        return categoryService.updateCategory(category_id, request);
    }

    @DeleteMapping("/{category_id}")
    public ApiResponse deleteCategory(@PathVariable(name = "category_id") Long category_id) {
        return categoryService.deleteCategory(category_id);
    }
}
