package com.goormthon.tomado.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class UserTomadoId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tomado_id")
    private Long tomadoId;

    public UserTomadoId(Long userId, Long tomadoId) {
        this.userId = userId;
        this.tomadoId = tomadoId;
    }

}
