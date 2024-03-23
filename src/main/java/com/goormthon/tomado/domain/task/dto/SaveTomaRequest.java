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

    public SaveTomaRequest(Long user_id, Long task_id, LocalDateTime created_at) {
        this.user_id = user_id;
        this.task_id = task_id;
        this.created_at = created_at;
    }
}
