package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.club.dto.ClubCreateDto;
import com.goormthon.tomado.domain.club.dto.ClubUpdateDto;
import com.goormthon.tomado.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("clubs")
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ApiResponse<ClubCreateDto.Response> createClub(@RequestBody ClubCreateDto.Request request) {
        return clubService.createClub(request);
    }

    @PutMapping
    public ApiResponse<ClubCreateDto.Response> updateClub(@RequestBody ClubUpdateDto.Request request) {
        return clubService.updateClub(request);
    }

    @DeleteMapping
    public ApiResponse deleteClub(@RequestParam(name = "club") Long club_id, @RequestParam(name = "user") Long user_id) {
        return clubService.deleteClub(club_id, user_id);
    }
}
