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

        private Request(Long user_id, Long category_id, String title, LocalDateTime created_at) {
            this.user_id = user_id;
            this.category_id = category_id;
            this.title = title;
            this.created_at = created_at;
        }
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
