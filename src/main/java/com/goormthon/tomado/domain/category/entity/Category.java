package com.goormthon.tomado.domain.category.entity;

import com.goormthon.tomado.domain.task.entity.Task;
import com.goormthon.tomado.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ColorType color;

    @Column
    private int tomato = 0; // 토마토 초기값 : 0

    @OneToMany(mappedBy = "category")
    private final List<Task> taskList = new ArrayList<>();

    public Category(User user, String title, ColorType color) {
        this.user = user;
        this.title = title;
        this.color = color;
    }

}
