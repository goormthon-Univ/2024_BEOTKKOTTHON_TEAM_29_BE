package com.goormthon.tomado.domain.memo.dto;

import com.goormthon.tomado.domain.memo.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemoDto {

    @Getter
    @NoArgsConstructor
    public static class Write {

        private String content;

        public Write(String content) {
            this.content = content;
        }

    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long id;
        private String content;
        private LocalDateTime created_at;

        public Response(Long id, String content, LocalDateTime created_at) {
            this.id = id;
            this.content = content;
            this.created_at = created_at;
        }

    }

    @Getter
    @NoArgsConstructor
    public static class ResponseList {

        private List<Response> memoList;

        public ResponseList(List<Response> memoList) {
            this.memoList = memoList;
        }

    }

    public static Response from(Memo memo) {
        return new Response(memo.getId(), memo.getContent(), memo.getCreatedAt());
    }

    public static ResponseList from(List<Memo> memoList) {
        return new ResponseList(memoList.stream()
                .map(memo -> from(memo))
                .collect(Collectors.toList()));
    }

}
