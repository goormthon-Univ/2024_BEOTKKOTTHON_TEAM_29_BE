package com.goormthon.tomado.domain.task.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;

import com.goormthon.tomado.domain.task.dto.SaveTomaRequest;
import com.goormthon.tomado.domain.task.dto.TomaCount;
import com.goormthon.tomado.domain.task.dto.TomaCountListResponse;

import com.goormthon.tomado.domain.task.dto.TaskCreateDto;
import com.goormthon.tomado.domain.task.entity.Task;
import com.goormthon.tomado.domain.task.repository.TaskRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public ApiResponse saveToma(SaveTomaRequest request) {
        Task task = taskRepository.findByIdAndUserId(request.getTask_id(), request.getUser_id())
                .orElseThrow(() -> new NotFoundException(TASK_NOT_EXIST));
        Category category = task.getCategory();
        User user = task.getUser();

        int toma = request.getMode() == 0 ? 1 : 3;

        if (!request.getCreated_at().toLocalDate().isEqual(task.getCreatedAt().toLocalDate())) {
            task = new Task(user, task.getTitle(), category);
            taskRepository.save(task);
        }

        taskRepository.save(task.addToma(toma));
        categoryRepository.save(category.addToma(toma));
        userRepository.save(user.addToma(toma));

        return ApiResponse.success(TOMA_SAVE_SUCCESS);
    }

    public ApiResponse<TomaCountListResponse> getTomaCountByMonth(Long userId, int month) {
        List<Task> taskList = taskRepository.findByUserAndMonth(userId, month);
        List<TomaCount> tomaCountListResponseList = calculateTomaCounts(taskList);

        return ApiResponse.success(TASK_TOMA_LIST_FETCH_SUCCESS, TomaCountListResponse.from(tomaCountListResponseList));
    }

    private List<TomaCount> calculateTomaCounts(List<Task> taskList) {
        Map<LocalDate, Integer> tomaCountMap = new HashMap<>();
        for (Task task : taskList) {
            LocalDate date = task.getCreatedAt().toLocalDate();
            int tomaCount = tomaCountMap.getOrDefault(date, 0) + task.getTomato();
            tomaCountMap.put(date, tomaCount);
        }

        return tomaCountMap.entrySet().stream()
                .map(entry -> new TomaCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(TomaCount::getDate))
                .collect(Collectors.toList());
    }

}
