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

import static com.goormthon.tomado.common.response.ErrorMessage.CATEGORY_NOT_EXIST;
import static com.goormthon.tomado.common.response.ErrorMessage.CATEGORY_TITLE_EXISTS;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final private CategoryRepository categoryRepository;
    final private UserRepository userRepository;

    public ApiResponse<CategoryCreateDto.Response> createCategory(CategoryCreateDto.Request request) {
        User user = userRepository.findByLoginId(request.getLogin_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_LOGIN_ID_NOT_EXIST));

        if (categoryRepository.existsByTitle(request.getTitle())) {
            throw new BadRequestException(CATEGORY_TITLE_EXISTS);
        }

        Category category = new Category(user, request.getTitle(), request.getColor());
        categoryRepository.save(category);

        return ApiResponse.success(CATEGORY_SAVE_SUCCESS, CategoryCreateDto.from(category));
    }

    public ApiResponse<CategoryListDto> findAllCategories(String login_id) {
        User user = userRepository.findByLoginId(login_id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_LOGIN_ID_NOT_EXIST));

        return ApiResponse.success(CATEGORY_LIST_FETCH_SUCCESS, CategoryListDto.from(user.getCategoryList()));
    }

    public ApiResponse<CategoryDto> updateCategory(Long category_id, CategoryUpdateDto.Request request) {
        Category category =  categoryRepository.findById(category_id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_EXIST));

        try {
            Category categoryUpdated = categoryRepository.save(category.update(request));
            return ApiResponse.success(CATEGORY_UPDATE_SUCCESS, CategoryUpdateDto.from(categoryUpdated));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(CATEGORY_NOT_EXIST);
        }
    }
}
