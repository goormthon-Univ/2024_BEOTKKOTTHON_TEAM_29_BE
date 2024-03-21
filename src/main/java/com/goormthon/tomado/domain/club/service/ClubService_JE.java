package com.goormthon.tomado.domain.club.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.common.response.SuccessMessage;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.club.dto.ClubGetDto;
import com.goormthon.tomado.domain.club.entity.Club;
import com.goormthon.tomado.domain.club.entity.ClubMembers;
import com.goormthon.tomado.domain.club.repository.ClubMembersRepository;
import com.goormthon.tomado.domain.club.repository.ClubRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService_JE {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ClubMembersRepository clubMembersRepository;


    public ApiResponse<ClubGetDto.Response> getClub(Long userId, Long clubId) {

        // 회원 & 클럽 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));
        Club club = clubRepository
                .findById(clubId).orElseThrow(() -> new NotFoundException(ErrorMessage.CLUB_NOT_EXIST));

        // 클럽 회원인지 확인
        clubMembersRepository.findByUserIdAndClubId(user.getId(), club.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_CLUB_MEMBER));

        // club에 있는 member 정보 저장
        List<ClubGetDto.ClubMember> clubMemberList = new ArrayList<>();
        for (ClubMembers member : club.getClubMembersList()) {
            clubMemberList.add(ClubGetDto.ClubMember.from(member.getUser()));
        }

        return ApiResponse.success(SuccessMessage.CLUB_FETCH_SUCCESS, ClubGetDto.Response.from(club, clubMemberList));

    }
}
