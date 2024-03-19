package com.goormthon.tomado.domain.category.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.domain.category.dto.CategoryCreateDto;
import com.goormthon.tomado.domain.category.dto.CategoryDto;
import com.goormthon.tomado.domain.category.dto.CategoryListDto;
import com.goormthon.tomado.domain.category.dto.CategoryUpdateDto;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    final private CategoryRepository categoryRepository;
    final private UserRepository userRepository;

    public ApiResponse<CategoryCreateDto.Response> createCategory(CategoryCreateDto.Request request) {
        User user = getUserByLoginId(request.getLogin_id());
        handleDuplicateTitleException(user, request.getTitle());

        Category category = new Category(user, request.getTitle(), request.getColor());
        categoryRepository.save(category);

        return ApiResponse.success(CATEGORY_SAVE_SUCCESS, CategoryCreateDto.from(category));
    }

    public ApiResponse<CategoryListDto> findAllCategories(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_LOGIN_ID_NOT_EXIST));

        return ApiResponse.success(CATEGORY_LIST_FETCH_SUCCESS, CategoryListDto.from(user.getCategoryList()));
    }

    public ApiResponse<CategoryDto> updateCategory(Long category_id, CategoryUpdateDto.Request request) {
        Category category =  getCategoryByCategoryId(category_id);
        User user = getUserByLoginId(request.getLogin_id());

        handleDuplicateTitleException(user, request.getTitle());

        try {
            Category categoryUpdated = categoryRepository.save(category.update(request));
            return ApiResponse.success(CATEGORY_UPDATE_SUCCESS, CategoryUpdateDto.from(categoryUpdated));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(CATEGORY_NOT_EXIST);
        }
    }

    public ApiResponse deleteCategory(Long category_id) {
        Category category =  getCategoryByCategoryId(category_id);
        categoryRepository.delete(category);
        return ApiResponse.success(CATEGORY_DELETE_SUCCESS);
    }

    private User getUserByLoginId(String loinId) {
        User user = userRepository.findByLoginId(loinId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_LOGIN_ID_NOT_EXIST));
        return user;
    }

    private Category getCategoryByCategoryId(Long categoryId) {
        Category category =  categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_EXIST));
        return category;
    }

    private void handleDuplicateTitleException(User user, String title) {
        List<Category> categoryList = categoryRepository.findByUser(user);
        boolean titleExists = categoryList.stream()
                .anyMatch(category -> category.getTitle().equals(title));
        if (titleExists) {
            throw new BadRequestException(CATEGORY_TITLE_EXISTS);
        }
    }
}
