package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.club.dto.ClubCreateDto;
import com.goormthon.tomado.domain.club.dto.ClubDto;
import com.goormthon.tomado.domain.club.dto.ClubGetDto;
import com.goormthon.tomado.domain.club.dto.ClubUpdateDto;
import com.goormthon.tomado.domain.club.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "클럽 정보 조회 - 단일")
    @GetMapping
    public ApiResponse<ClubGetDto.Response> getClub(@RequestParam(name = "user") Long userId, @RequestParam(name = "club") Long clubId) {
        return clubService.getClub(userId, clubId);
    }

    @Operation(summary = "클럽 정보 조회 - 리스트")
    @GetMapping("/{user_id}")
    public ApiResponse<ClubGetDto.ResponseList> getClubList(@PathVariable(name = "user_id") Long userId) {
        return clubService.getClubList(userId);
    }

    @Operation(summary = "클럽 가입")
    @PostMapping("/join")
    public ApiResponse joinClub(@RequestBody ClubDto.Join request) {
        return clubService.joinClub(request);
    }
}
