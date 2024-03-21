package com.goormthon.tomado.domain.club.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.club.dto.ClubDto;
import com.goormthon.tomado.domain.club.dto.ClubGetDto;
import com.goormthon.tomado.domain.club.entity.Club;
import com.goormthon.tomado.domain.club.entity.ClubMembers;
import com.goormthon.tomado.domain.club.entity.ClubMembersId;
import com.goormthon.tomado.domain.club.repository.ClubMembersRepository;
import com.goormthon.tomado.domain.club.repository.ClubRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.goormthon.tomado.common.response.SuccessMessage.CLUB_FETCH_SUCCESS;
import static com.goormthon.tomado.common.response.SuccessMessage.CLUB_JOIN_SUCCESS;

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
        boolean isClubMember = club.getClubMembersList().stream()
                .anyMatch(clubMembers -> clubMembers.getUser().equals(user));
        if (isClubMember) {
            throw new BadRequestException(ErrorMessage.USER_NOT_CLUB_MEMBER);
        }

        // club에 있는 member 정보 저장
        List<ClubGetDto.ClubMember> clubMemberList = new ArrayList<>();
        for (ClubMembers member : club.getClubMembersList()) {
            clubMemberList.add(ClubGetDto.ClubMember.from(member.getUser()));
        }

        return ApiResponse.success(CLUB_FETCH_SUCCESS, ClubGetDto.Response.from(club, clubMemberList));

    }

    public ApiResponse joinClub(ClubDto.Join request) {
        // 회원 & 클럽 확인
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));
        Club club = clubRepository.findById(request.getClub_id())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CLUB_NOT_EXIST));

        // 이미 가입한 클럽인지 확인
        for (ClubMembers clubMembers : user.getClubList()) {
            if (clubMembers.getClub().equals(club)) {
                throw new BadRequestException(ErrorMessage.USER_CLUB_ALREADY_EXIST);
            }
        }

        // 클럽 회원 수 확인
        if (club.getMemberNumber() <= club.getClubMembersList().size()) {
            throw new BadRequestException(ErrorMessage.CLUB_MEMBER_NUMBER_FULL);
        }

        // 클럽 -> 카테고리 생성
        Category category = new Category(user, club.getTitle(), club.getColor());
        categoryRepository.save(category.checkClub());

        // clubMembers 생성
        ClubMembersId clubMembersId = new ClubMembersId(club.getId(), user.getId());
        ClubMembers clubMembers = new ClubMembers(clubMembersId, club, user, category);
        clubMembersRepository.save(clubMembers);

        user.getClubList().add(clubMembers);
        club.getClubMembersList().add(clubMembers);

        userRepository.save(user);
        clubRepository.save(club);

        return ApiResponse.success(CLUB_JOIN_SUCCESS);
    }
}
