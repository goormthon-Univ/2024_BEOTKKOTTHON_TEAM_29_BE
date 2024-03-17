package com.goormthon.tomado.domain.club.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

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

    public Club(String title, int memberNumber, int goal, String memo, String url, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.memberNumber = memberNumber;
        this.goal = goal;
        this.memo = memo;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
