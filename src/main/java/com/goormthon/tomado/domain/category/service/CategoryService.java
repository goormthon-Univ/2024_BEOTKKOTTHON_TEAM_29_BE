package com.goormthon.tomado.domain.category.service;

import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final private CategoryRepository categoryRepository;
}
