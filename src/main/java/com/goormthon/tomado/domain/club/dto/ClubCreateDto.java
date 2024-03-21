package com.goormthon.tomado.domain.club.dto;

import com.goormthon.tomado.domain.category.entity.ColorType;
import com.goormthon.tomado.domain.club.entity.Club;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ClubCreateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private Long user_id;
        private String title;
        private ColorType color;
        private int member_number;
        private int goal;
        private String memo;
        private LocalDate start_date;
        private LocalDate end_date;

        private Request(Long user_id, String title, ColorType color, int member_number, int goal, String memo, LocalDate start_date, LocalDate end_date) {
            this.user_id = user_id;
            this.title = title;
            this.color = color;
            this.member_number = member_number;
            this.goal = goal;
            this.memo = memo;
            this.start_date = start_date;
            this.end_date = end_date;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long club_id;
        private String title;
        private ColorType color;
        private int current_amount;
        private int member_number;
        private int goal;
        private String memo;
        private LocalDate start_date;
        private LocalDate end_date;

        private Response(Long club_id, String title, ColorType color, int current_amount, int member_number, int goal, String memo, LocalDate start_date, LocalDate end_date) {
            this.club_id = club_id;
            this.title = title;
            this.color = color;
            this.current_amount = current_amount;
            this.member_number = member_number;
            this.goal = goal;
            this.memo = memo;
            this.start_date = start_date;
            this.end_date = end_date;
        }
    }

    public static Response from(Club club) {
        return new Response(club.getId(), club.getTitle(), club.getColor(), club.getCurrentAmount(), club.getMemberNumber(), club.getGoal(), club.getMemo(), club.getStartDate(), club.getEndDate());
    }
}
