package com.goormthon.tomado.domain.club.entity;

import com.goormthon.tomado.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMembers {

    @EmbeddedId
    private ClubMembersId id;

    @MapsId("clubId")
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int tomato = 0; // 토마토 초기값 : 0

    public ClubMembers(Club club, User user) {
        this.club = club;
        this.user = user;
    }

}
