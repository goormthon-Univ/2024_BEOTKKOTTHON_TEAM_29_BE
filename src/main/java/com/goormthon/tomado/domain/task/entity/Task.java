package com.goormthon.tomado.domain.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goormthon.tomado.domain.category.entity.Category;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(nullable = true)
    private String title;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private int tomato = 0; // 토마토 초기값 : 0

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Task(User user, String title, Category category) {
        this.user = user;
        this.title = title;
        this.category = category;
    }

}
