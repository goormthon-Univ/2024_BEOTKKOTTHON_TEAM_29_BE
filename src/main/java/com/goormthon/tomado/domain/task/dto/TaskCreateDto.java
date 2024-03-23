package com.goormthon.tomado.domain.task.dto;

import com.goormthon.tomado.domain.task.entity.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TaskCreateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private Long user_id;
        private Long category_id;
        private String title;
        private LocalDateTime created_at;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private Long task_id;
        private String title;

        public Response(Long task_id, String title) {
            this.task_id = task_id;
            this.title = title;
        }
    }

    public static Response from(Task task) {
        return new Response(task.getId(), task.getTitle());
    }
}
