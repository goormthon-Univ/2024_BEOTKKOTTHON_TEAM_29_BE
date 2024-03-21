package com.goormthon.tomado.domain.club.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.category.repository.CategoryRepository;
import com.goormthon.tomado.domain.club.dto.ClubCreateDto;
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

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.CLUB_CREATE_SUCCESS;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ClubMembersRepository clubMembersRepository;

    public ApiResponse<ClubCreateDto.Response> createClub(ClubCreateDto.Request request) {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));

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
}
