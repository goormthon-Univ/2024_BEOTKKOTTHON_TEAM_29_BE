package com.goormthon.tomado.domain.club.dto;

import com.goormthon.tomado.domain.category.entity.ColorType;
import com.goormthon.tomado.domain.club.entity.Club;
import com.goormthon.tomado.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ClubGetDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long club_id;
        private String title;
        private List<ClubMember> MemberList;
        private int goal;
        private int current_amount;
        private String memo;
        private LocalDate start_date;
        private LocalDate end_date;
        private boolean completed;
        private String club_url;

        public Response(Long club_id, String title, List<ClubMember> memberList, int goal, int current_amount, String memo, LocalDate start_date, LocalDate end_date, boolean completed, String club_url) {
            this.club_id = club_id;
            this.title = title;
            this.MemberList = memberList;
            this.goal = goal;
            this.current_amount = current_amount;
            this.memo = memo;
            this.start_date = start_date;
            this.end_date = end_date;
            this.completed = completed;
            this.club_url = club_url;
        }

        public static Response from(Club club, List<ClubMember> clubMemberList) {
            return new Response(club.getId(), club.getTitle(), clubMemberList, club.getGoal(), club.getCurrentAmount(), club.getMemo(), club.getStartDate(), club.getEndDate(), club.isCompleted(), club.getUrl());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ResponseList {
        private List<Response> clubList;

        public ResponseList(List<Response> clubList) {
            this.clubList = clubList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ClubMember {
        private Long user_id;
        private String nickname;
        private String url;
        private int tomato;

        private ClubMember(Long user_id, String nickname, String url, int tomato) {
            this.user_id = user_id;
            this.nickname = nickname;
            this.url = url;
            this.tomato = tomato;
        }

        public static ClubMember from(User user) {
            return new ClubMember(user.getId(), user.getNickname(), user.getCharacterUrl(), user.getTomato());
        }
    }

}
