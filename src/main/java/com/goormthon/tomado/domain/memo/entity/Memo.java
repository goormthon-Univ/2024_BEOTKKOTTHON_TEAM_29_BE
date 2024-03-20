package com.goormthon.tomado.domain.memo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goormthon.tomado.domain.user.entity.User;
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
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    public Memo(User user, String content) {
        this.user = user;
        this.content = content;
    }

}
