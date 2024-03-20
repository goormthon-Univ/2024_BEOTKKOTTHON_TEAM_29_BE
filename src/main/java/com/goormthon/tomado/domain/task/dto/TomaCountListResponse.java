package com.goormthon.tomado.domain.task.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TomaCountListResponse {
    private List<TomaCount> tomaCountList;

    public TomaCountListResponse(List<TomaCount> tomaCountList) {
        this.tomaCountList = tomaCountList;
    }

    public static TomaCountListResponse from(List<TomaCount> tomaCountList) {
        return new TomaCountListResponse(tomaCountList);
    }
}
