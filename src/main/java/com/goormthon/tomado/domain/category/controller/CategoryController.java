package com.goormthon.tomado.domain.category.controller;

import com.goormthon.tomado.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    final private CategoryService categoryService;
}
