package com.goormthon.tomado.domain.club.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

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
        Club club = getClubByClubId(request.getClub_id());

        if (isClubMember(club, user)) {
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

    public ApiResponse deleteClub(Long club_id, Long user_id) {
        User user = getUserByUserId(user_id);
        Club club = getClubByClubId(club_id);

        if (isClubMember(club, user)) {
            List<ClubMembers> clubMembersList = club.getClubMembersList();

            // 클럽 멤버가 user 1명 & 클럽의 current_amount가 0 -> Club 삭제(연결된 ClubMembers, Category 같이 삭제)
            if (clubMembersList.size() == 1 && club.getCurrentAmount() == 0) {
                deleteAll(clubMembersList, club);
            } else {
                // ClubMembers만 삭제 (Club, Category 그대로)

                ClubMembers memberToDelete = findMemberToDelete(clubMembersList, user);

                // 카테고리의 tomato가 0이면 삭제
                if (memberToDelete.getCategory().getTomato() == 0) {
                    categoryRepository.delete(memberToDelete.getCategory());
                } else {
                    // 카테고리 isDeleted true로 변경
                    categoryRepository.save(memberToDelete.getCategory().delete());
                }

                // 클럽의 리스트에서 해당 멤버 삭제
                clubMembersList.remove(memberToDelete);
                clubRepository.save(club);
                // ClubMembers 삭제
                clubMembersRepository.delete(memberToDelete);
            }
        } else {
            throw new BadRequestException(USER_NOT_CLUB_MEMBER);
        }

        return ApiResponse.success(CLUB_DELETE_SUCCESS);
    }

    private User getUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
        return user;
    }

    private Club getClubByClubId(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NotFoundException(CLUB_NOT_EXIST));
        return club;
    }

    private boolean isClubMember(Club club, User user) {
        return club.getClubMembersList().stream()
                .anyMatch(clubMembers -> clubMembers.getUser().equals(user));
    }

    private void deleteAll(List<ClubMembers> clubMembersList, Club club) {
        // Category 삭제
        Category category = clubMembersList.get(0).getCategory();
        categoryRepository.delete(category);

        // Club 삭제 -> ClubMembers 삭제
        clubRepository.delete(club);
    }

    private ClubMembers findMemberToDelete(List<ClubMembers> clubMembersList, User user) {
        ClubMembers member = null;
        for (ClubMembers clubMembers : clubMembersList) {
            if (clubMembers.getUser().equals(user)) {
                member = clubMembers;
                break;
            }
        }
        return member;
    }
}
