package com.goormthon.tomado.domain.club.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.club.dto.ClubCreateDto;
import com.goormthon.tomado.domain.club.dto.ClubUpdateDto;
import com.goormthon.tomado.domain.club.entity.Club;
import com.goormthon.tomado.domain.club.entity.ClubMembers;
import com.goormthon.tomado.domain.club.entity.ClubMembersId;
import com.goormthon.tomado.domain.club.repository.ClubMembersRepository;
import com.goormthon.tomado.domain.club.repository.ClubRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.CLUB_CREATE_SUCCESS;
import static com.goormthon.tomado.common.response.SuccessMessage.CLUB_UPDATE_SUCCESS;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ClubMembersRepository clubMembersRepository;

    public ApiResponse<ClubCreateDto.Response> createClub(ClubCreateDto.Request request) {
        User user = getUserByUserId(request.getUser_id());

        // Club 생성
        Club club = new Club(request.getTitle(), request.getColor(), request.getMember_number(), request.getGoal(), request.getMemo(), request.getStart_date(), request.getEnd_date());
        clubRepository.save(club);

        // Category 생성
        Category category = new Category(user, request.getTitle(), request.getColor());
        categoryRepository.save(category.checkClub());

        // ClubMembers 생성
        ClubMembersId clubMembersId = new ClubMembersId(club.getId(), user.getId());
        ClubMembers clubMembers = new ClubMembers(clubMembersId, club, user, category);
        clubMembersRepository.save(clubMembers);

        user.getClubList().add(clubMembers);
        club.getClubMembersList().add(clubMembers);
        userRepository.save(user);
        clubRepository.save(club);

        return ApiResponse.success(CLUB_CREATE_SUCCESS, ClubCreateDto.from(club));
    }

    public ApiResponse<ClubCreateDto.Response> updateClub(ClubUpdateDto.Request request) {
        User user = getUserByUserId(request.getUser_id());
        Club club = clubRepository.findById(request.getClub_id())
                .orElseThrow(() -> new NotFoundException(CLUB_NOT_EXIST));

        // user가 club의 멤버인지 확인
        boolean isMember = club.getClubMembersList().stream()
                .anyMatch(clubMembers -> clubMembers.getUser().equals(user));

        if (isMember) {
            // 수정하려는 클럽의 정원 < 현재 클럽에 가입된 멤버 수
            if (request.getMember_number() < club.getClubMembersList().size()) {
                throw new BadRequestException(CLUB_MEMBER_NUMBER_FULL);
            }

            // 수정하려는 목표 토마토 개수 < 현재 적립된 토마토 수 or 범위 벗어나는 값
            if (request.getGoal() < club.getCurrentAmount() || request.getGoal() < 0) {
                throw new BadRequestException(INVALID_GOAL);
            }

            // 클럽 수정
            Club clubUpdated = clubRepository.save(club.update(request));
            // ClubMembers -> Category 찾아 수정
            List<ClubMembers> clubMembersList = clubUpdated.getClubMembersList();
            for (ClubMembers member : clubMembersList) {
                Category category = member.getCategory();
                categoryRepository.save(category.update(request.getTitle(), request.getColor()));
            }
            return ApiResponse.success(CLUB_UPDATE_SUCCESS, ClubCreateDto.from(clubUpdated));
        } else {
            throw new BadRequestException(USER_NOT_CLUB_MEMBER);
        }
    }

    private User getUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return user;
    }
}
