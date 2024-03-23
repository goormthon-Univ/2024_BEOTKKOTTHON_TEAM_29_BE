package com.goormthon.tomado.domain.club.dto;

import com.goormthon.tomado.domain.category.entity.ColorType;
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

        private Request(Long user_id, Long club_id, String title, int member_number, int goal, String memo, LocalDate end_date) {
            this.user_id = user_id;
            this.club_id = club_id;
            this.title = title;
            this.member_number = member_number;
            this.goal = goal;
            this.memo = memo;
            this.end_date = end_date;
        }
    }
}
