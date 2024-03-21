package com.goormthon.tomado.domain.user.dto;

import com.goormthon.tomado.domain.user.entity.UserTomado;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class BookResponse {

    @Getter
    @NoArgsConstructor
    public static class Simple {
        private Long tomado_id;
        private String url;
        private String name;
        private LocalDateTime created_at;

        private Simple(Long tomado_id, String url, String name, LocalDateTime created_at) {
            this.tomado_id = tomado_id;
            this.url = url;
            this.name = name;
            this.created_at = created_at;
        }

        public static Simple from(UserTomado userTomado) {
            return new Simple(userTomado.getTomado().getId(), userTomado.getTomado().getUrl(), userTomado.getTomado().getName(), userTomado.getCreatedAt());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Detailed {
        private Long tomado_id;
        private String url;
        private String name;
        private String content;
        private int tomato;
        private LocalDateTime created_at;

        public Detailed(Long tomado_id, String url, String name, String content, int tomato, LocalDateTime created_at) {
            this.tomado_id = tomado_id;
            this.url = url;
            this.name = name;
            this.content = content;
            this.tomato = tomato;
            this.created_at = created_at;
        }

        public static Detailed from(UserTomado userTomado) {
            return new Detailed(userTomado.getTomado().getId(), userTomado.getTomado().getUrl(), userTomado.getTomado().getName(), userTomado.getTomado().getContent(), userTomado.getTomado().getTomato(), userTomado.getCreatedAt());
        }
    }


}
