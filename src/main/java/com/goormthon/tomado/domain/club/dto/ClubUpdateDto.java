package com.goormthon.tomado.domain.club.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ClubUpdateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private Long user_id;
        private Long club_id;
        private String title;
        private int member_number;
        private int goal;
        private String memo;
        private LocalDate end_date;
    }
}
