package com.goormthon.tomado.domain.user.repository;

import com.goormthon.tomado.domain.user.entity.UserTomado;
import com.goormthon.tomado.domain.user.entity.UserTomadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTomadoRepository extends JpaRepository<UserTomado, UserTomadoId> {
    Optional<List<UserTomado>> findByUserId(Long userId);

    Optional<UserTomado> findByUserIdAndTomadoId(Long userId, Long tomadoId);
}
