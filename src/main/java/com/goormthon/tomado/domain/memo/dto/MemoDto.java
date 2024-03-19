package com.goormthon.tomado.domain.memo.dto;

import com.goormthon.tomado.domain.memo.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemoDto {

    @Getter
    @NoArgsConstructor
    public static class Write {

        private String content;

        public Write(String content) {
            this.content = content;
        }

    }

}
