package com.goormthon.tomado.domain.club.entity;

import com.goormthon.tomado.domain.category.entity.Category;
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

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public ClubMembers(ClubMembersId id, Club club, User user, Category category) {
        this.id = id;
        this.club = club;
        this.user = user;
        this.category = category;
    }

}
