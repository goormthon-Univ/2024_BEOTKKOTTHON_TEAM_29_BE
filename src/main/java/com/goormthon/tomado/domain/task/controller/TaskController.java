package com.goormthon.tomado.domain.task.controller;

import com.goormthon.tomado.common.ApiResponse;

import com.goormthon.tomado.domain.task.dto.SaveTomaRequest;
import com.goormthon.tomado.domain.task.dto.TaskCreateDto;
import com.goormthon.tomado.domain.task.dto.TomaCountListResponse;
import com.goormthon.tomado.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    private static final int TOMA = 1;
    private static final int HARD = 3;

    @PostMapping
    public ApiResponse<TaskCreateDto.Response> createTask(@RequestBody TaskCreateDto.Request request) {
        return taskService.createTask(request);
    }

    @PostMapping("/toma")
    public ApiResponse<SaveTomaRequest.NewTaskId> saveToma(@RequestBody SaveTomaRequest request) {
        return taskService.saveToma(request, TOMA);
    }

    @GetMapping
    public ApiResponse<TomaCountListResponse> getTomaCountByMonth(@RequestParam("user") Long user_id, @RequestParam("month") int month) {
        return taskService.getTomaCountByMonth(user_id, month);
    }

    @PostMapping("/hard")
    public ApiResponse<SaveTomaRequest.NewTaskId> checkHardMode(@RequestBody SaveTomaRequest request) {
        return taskService.checkHardMode(request, HARD);
    }

}
