package com.goormthon.tomado.domain.task.controller;

import com.goormthon.tomado.common.ApiResponse;

import com.goormthon.tomado.domain.task.dto.SaveTomaRequest;
import com.goormthon.tomado.domain.task.dto.TaskCreateDto;
import com.goormthon.tomado.domain.task.dto.TomaCountListResponse;
import com.goormthon.tomado.domain.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    private static final int TOMA = 1;
    private static final int HARD = 3;

    @Operation(summary = "task 생성")
    @PostMapping
    public ApiResponse<TaskCreateDto.Response> createTask(@RequestBody TaskCreateDto.Request request) {
        return taskService.createTask(request);
    }

    @Operation(summary = "뽀모도로 적립")
    @PostMapping("/toma")
    public ApiResponse<SaveTomaRequest.NewTaskId> saveToma(@RequestBody SaveTomaRequest request) {
        return taskService.saveToma(request, TOMA);
    }

    @Operation(summary = "달력 - 월 별로 뽀모도로 개수 확인")
    @GetMapping
    public ApiResponse<TomaCountListResponse> getTomaCountByMonth(@RequestParam("user") Long user_id, @RequestParam("month") int month) {
        return taskService.getTomaCountByMonth(user_id, month);
    }

    @Operation(summary = "하드 뽀모도로 적립")
    @PostMapping("/hard")
    public ApiResponse<SaveTomaRequest.NewTaskId> checkHardMode(@RequestBody SaveTomaRequest request) {
        return taskService.checkHardMode(request, HARD);
    }

}
