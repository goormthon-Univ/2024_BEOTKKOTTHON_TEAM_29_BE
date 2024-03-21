package com.goormthon.tomado.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goormthon.tomado.domain.tomado.entity.Tomado;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTomado {

    @EmbeddedId
    private UserTomadoId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @MapsId("tomadoId")
    @ManyToOne
    @JoinColumn(name = "tomado_id")
    @JsonBackReference
    private Tomado tomado;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    public UserTomado(User user, Tomado tomado) {
        this.id = new UserTomadoId(user.getId(), tomado.getId());
        this.user = user;
        this.tomado = tomado;
    }

}
