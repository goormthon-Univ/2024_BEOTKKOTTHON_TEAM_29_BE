package com.goormthon.tomado.domain.category.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.category.dto.*;
import com.goormthon.tomado.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    final private CategoryService categoryService;

    @Operation(summary = "카테고리 생성")
    @PostMapping
    public ApiResponse<CategoryCreateDto.Response> createCategory(@RequestBody CategoryCreateDto.Request request) {
        return categoryService.createCategory(request);
    }

    @Operation(summary = "카테고리 조회")
    @GetMapping("/{user_id}")
    public ApiResponse<CategoryListDto> findAllCategories(@PathVariable(name = "user_id") Long user_id) {
        return categoryService.findAllCategories(user_id);
    }

    @Operation(summary = "클럽 카테고리 조회")
    @GetMapping("club/{user_id}")
    public ApiResponse<CategoryListDto> findAllClubCategories(@PathVariable(name = "user_id") Long user_id) {
        return categoryService.findAllClubCategories(user_id);
    }

    @Operation(summary = "달력 - 날짜 별 카테고리 조회")
    @GetMapping
    public ApiResponse<CategoryListByDateDto> findCategoriesByDate(
            @RequestParam("user") Long user_id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selected_date) {
        return categoryService.findCategoriesByDate(user_id, selected_date);
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{category_id}")
    public ApiResponse<SimpleResponse> updateCategory(@PathVariable(name = "category_id") Long category_id, @RequestBody CategoryUpdateDto.Request request) {
        return categoryService.updateCategory(category_id, request);
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping
    public ApiResponse deleteCategory(@RequestParam(name = "user") Long user_id, @RequestParam(name = "category") Long category_id) {
        return categoryService.deleteCategory(category_id, user_id);
    }
}
