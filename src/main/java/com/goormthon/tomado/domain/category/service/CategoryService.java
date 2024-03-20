package com.goormthon.tomado.domain.category.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.category.dto.*;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.task.entity.Task;
import com.goormthon.tomado.domain.task.repository.TaskRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    final private CategoryRepository categoryRepository;
    final private UserRepository userRepository;
    final private TaskRepository taskRepository;

    public ApiResponse<CategoryCreateDto.Response> createCategory(CategoryCreateDto.Request request) {
        User user = getUserByUserId(request.getUser_id());
        handleDuplicateTitleException(user, request.getTitle());

        Category category = new Category(user, request.getTitle(), request.getColor());
        categoryRepository.save(category);

        return ApiResponse.success(CATEGORY_SAVE_SUCCESS, CategoryCreateDto.from(category));
    }

    public ApiResponse<CategoryListDto> findAllCategories(Long user_id) {
        User user = getUserByUserId(user_id);

        return ApiResponse.success(CATEGORY_LIST_FETCH_SUCCESS, CategoryListDto.from(user.getCategoryList()));
    }

    public ApiResponse<CategoryListByDateDto> findCategoriesByDate(Long user_id, LocalDate selected_date) {
        User user = getUserByUserId(user_id);
        List<Task> taskList = taskRepository.findByUserAndDate(user, selected_date);

        return ApiResponse.success(CATEGORY_LIST_FETCH_SUCCESS, CategoryListByDateDto.fromCategoriesByDate(taskList));
    }

    public ApiResponse<SimpleResponse> updateCategory(Long category_id, CategoryUpdateDto.Request request) {
        Category category =  getCategory(category_id, request.getUser_id());
        User user = getUserByUserId(request.getUser_id());

        if (!category.getTitle().equals(request.getTitle())) {
            handleDuplicateTitleException(user, request.getTitle());
        }

        try {
            Category categoryUpdated = categoryRepository.save(category.update(request));
            return ApiResponse.success(CATEGORY_UPDATE_SUCCESS, CategoryUpdateDto.from(categoryUpdated));
        } catch (DataIntegrityViolationException e) {
            throw new NotFoundException(CATEGORY_NOT_EXIST);
        }
    }

    public ApiResponse deleteCategory(Long category_id, Long user_id) {
        Category category =  getCategory(category_id, user_id);

        if (category.getTomato() == 0) {
            List<Task> taskList = category.getTaskList();
            for (Task task : taskList) {
                taskRepository.delete(task);
            }
            categoryRepository.delete(category);
        } else {
            categoryRepository.save(category.delete());
        }
        return ApiResponse.success(CATEGORY_DELETE_SUCCESS);
    }

    private User getUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return user;
    }

    private Category getCategory(Long categoryId, Long userId) {
        Category category =  categoryRepository.findByIdAndUser_Id(categoryId, userId)
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

