package com.goormthon.tomado.domain.club.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ClubDto {

    @Getter
    @NoArgsConstructor
    public static class Join {
        private Long user_id;
        private Long club_id;
    }

}
