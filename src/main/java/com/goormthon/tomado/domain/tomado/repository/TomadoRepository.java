package com.goormthon.tomado.domain.tomado.repository;

import com.goormthon.tomado.domain.tomado.entity.Tomado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TomadoRepository extends JpaRepository<Tomado, Long> {
}
