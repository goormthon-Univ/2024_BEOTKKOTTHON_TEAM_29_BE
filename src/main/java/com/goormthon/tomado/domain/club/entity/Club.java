package com.goormthon.tomado.domain.club.entity;

import com.goormthon.tomado.domain.category.entity.ColorType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private ColorType color;

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

    public Club(String title, ColorType color, int memberNumber, int goal, String memo, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.color = color;
        this.memberNumber = memberNumber;
        this.goal = goal;
        this.memo = memo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
