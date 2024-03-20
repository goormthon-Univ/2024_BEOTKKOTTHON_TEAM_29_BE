package com.goormthon.tomado.domain.task.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.task.dto.TaskCreateDto;
import com.goormthon.tomado.domain.task.entity.Task;
import com.goormthon.tomado.domain.task.repository.TaskRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ApiResponse<TaskCreateDto.Response> createTask(TaskCreateDto.Request request) {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        Category category = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_EXIST));

        Task task = new Task(user, request.getTitle(), category);
        taskRepository.save(task);

        return ApiResponse.success(TASK_SAVE_SUCCESS, TaskCreateDto.from(task));
    }
}
