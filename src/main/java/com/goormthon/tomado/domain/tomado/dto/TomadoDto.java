package com.goormthon.tomado.domain.tomado.dto;

import com.goormthon.tomado.domain.tomado.entity.Tomado;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TomadoDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long tomado_id;
        private String url;
        private String name;
        private String content;
        private int tomato;

        private Response(Long tomado_id, String url, String name, String content, int tomato) {
            this.tomado_id = tomado_id;
            this.url = url;
            this.name = name;
            this.content = content;
            this.tomato = tomato;
        }
    }

    public static Response from(Tomado tomado) {
        return new Response(tomado.getId(), tomado.getUrl(), tomado.getName(), tomado.getContent(), tomado.getTomato());
    }

}
