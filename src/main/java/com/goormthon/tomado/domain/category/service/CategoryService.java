package com.goormthon.tomado.domain.category.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.domain.category.dto.CategoryCreateDto;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.goormthon.tomado.common.response.ErrorMessage.CATEGORY_TITLE_EXISTS;
import static com.goormthon.tomado.common.response.SuccessMessage.CATEGORY_SAVE_SUCCESS;

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
}
