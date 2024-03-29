package com.goormthon.tomado.domain.club.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.goormthon.tomado.domain.category.entity.ColorType;
import com.goormthon.tomado.domain.club.dto.ClubUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    @Enumerated(value = EnumType.STRING)
    private final ColorType color = ColorType.GRAY;

    @Column(nullable = false)
    private int memberNumber;

    @Column(nullable = false)
    private int goal;

    @Column
    private int currentAmount = 0; // 토마토 초기값 : 0

    @Column
    private String memo;

    @Column
    private String url;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private boolean isCompleted = false; // 완료 초기값 : false

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<ClubMembers> clubMembersList = new ArrayList<>();

    public Club(String title, int memberNumber, int goal, String memo, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.memberNumber = memberNumber;
        this.goal = goal;
        this.memo = memo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Club update(ClubUpdateDto.Request request) {
        this.title = title.equals(request.getTitle()) || request.getTitle() == null ? title : request.getTitle();
        this.memberNumber = request.getMember_number();
        this.goal = request.getGoal();
        this.memo = memo.equals(request.getMemo()) || request.getMemo() == null ? memo : request.getMemo();
        this.endDate = request.getEnd_date();

        return this;
    }

    public Club addToma(int toma) {
        currentAmount += toma;
        currentAmount = currentAmount >= goal ? goal : currentAmount;
        return this;
    }

    public Club complete() {
        this.isCompleted = true;
        return this;
    }

    public Club saveUrl(String url) {
        this.url = url;
        return this;
    }
}
