package com.goormthon.tomado.domain.task.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveTomaRequest {

    private Long user_id;
    private Long task_id;
    private LocalDateTime created_at;

    @Getter
    @NoArgsConstructor
    public static class NewTaskId {
        private Long task_id;

        public NewTaskId(Long task_id) {
            this.task_id = task_id;
        }
    }
}
