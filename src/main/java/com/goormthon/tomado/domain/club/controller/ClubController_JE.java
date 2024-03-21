package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.club.dto.ClubGetDto;
import com.goormthon.tomado.domain.club.service.ClubService;
import com.goormthon.tomado.domain.club.service.ClubService_JE;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clubs")
public class ClubController_JE {

    private final ClubService_JE clubService;

    @GetMapping
    public ApiResponse<ClubGetDto.Response> getClub(@RequestParam(name = "user") Long userId, @RequestParam(name = "club") Long clubId) {
        return clubService.getClub(userId, clubId);
    }
}
