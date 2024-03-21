package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.club.dto.ClubDto;
import com.goormthon.tomado.domain.club.dto.ClubGetDto;
import com.goormthon.tomado.domain.club.service.ClubService;
import com.goormthon.tomado.domain.club.service.ClubService_JE;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clubs")
public class ClubController_JE {

    private final ClubService_JE clubService;

    @Operation(summary = "클럽 정보 조회 - 단일")
    @GetMapping
    public ApiResponse<ClubGetDto.Response> getClub(@RequestParam(name = "user") Long userId, @RequestParam(name = "club") Long clubId) {
        return clubService.getClub(userId, clubId);
    }

    @GetMapping("/{user_id}")
    public ApiResponse<ClubGetDto.ResponseList> getClubList(@PathVariable(name = "user_id") Long userId) {
        return clubService.getClubList(userId);
    }

    @PostMapping("/join")
    public ApiResponse joinClub(@RequestBody ClubDto.Join request) {
        return clubService.joinClub(request);
    }
}
