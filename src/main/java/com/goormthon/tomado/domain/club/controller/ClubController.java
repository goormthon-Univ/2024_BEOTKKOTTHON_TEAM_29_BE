package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.club.dto.ClubCreateDto;
import com.goormthon.tomado.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("clubs")
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ApiResponse<ClubCreateDto.Response> createClub(@RequestBody ClubCreateDto.Request request) {
        return clubService.createClub(request);
    }
}
