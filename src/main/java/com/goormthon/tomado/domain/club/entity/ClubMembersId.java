package com.goormthon.tomado.domain.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ClubMembersId implements Serializable {

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "user_id")
    private Long userId;

    public ClubMembersId(Long clubId, Long userId) {
        this.clubId = clubId;
        this.userId = userId;
    }

}
