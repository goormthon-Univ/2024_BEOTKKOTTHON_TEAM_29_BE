package com.goormthon.tomado.domain.category.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.category.dto.CategoryCreateDto;
import com.goormthon.tomado.domain.category.dto.CategoryDto;
import com.goormthon.tomado.domain.category.dto.CategoryListDto;
import com.goormthon.tomado.domain.category.dto.CategoryUpdateDto;
import com.goormthon.tomado.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    final private CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryCreateDto.Response> createCategory(@RequestBody CategoryCreateDto.Request request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{login_id}")
    public ApiResponse<CategoryListDto> findAllCategories(@PathVariable(name = "login_id") String login_id) {
        return categoryService.findAllCategories(login_id);
    }

    @PutMapping("/{category_id}")
    public ApiResponse<CategoryDto> updateCategory(@PathVariable(name = "category_id") Long category_id, @RequestBody CategoryUpdateDto.Request request) {
        return categoryService.updateCategory(category_id, request);
    }

    @DeleteMapping("/{category_id}")
    public ApiResponse deleteCategory(@PathVariable(name = "category_id") Long category_id) {
        return categoryService.deleteCategory(category_id);
    }
}
