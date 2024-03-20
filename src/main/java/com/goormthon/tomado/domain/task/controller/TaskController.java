package com.goormthon.tomado.domain.task.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.task.dto.TaskCreateDto;
import com.goormthon.tomado.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ApiResponse<TaskCreateDto.Response> createTask(@RequestBody TaskCreateDto.Request request) {
        return taskService.createTask(request);
    }
}
