package com.goormthon.tomado.domain.task.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TomaCount {
    private LocalDate date;
    private int tomaCount;

    public TomaCount(LocalDate date, int tomaCount) {
        this.date = date;
        this.tomaCount = tomaCount;
    }
}
