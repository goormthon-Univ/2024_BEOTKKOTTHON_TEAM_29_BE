package com.goormthon.tomado.domain.club.repository;

import com.goormthon.tomado.domain.club.entity.ClubMembers;
import com.goormthon.tomado.domain.club.entity.ClubMembersId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubMembersRepository extends JpaRepository<ClubMembers, ClubMembersId> {
    Optional<ClubMembers> findByUserIdAndClubId(Long userId, Long clubId);
}
