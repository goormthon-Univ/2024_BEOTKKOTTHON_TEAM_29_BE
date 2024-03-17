package com.goormthon.tomado.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.goormthon.tomado.domain.category.entity.Category;
import com.goormthon.tomado.domain.memo.entity.Memo;
import com.goormthon.tomado.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String characterUrl = "https://i.ibb.co/1d94Q4f/image.png"; // 기본 캐릭터 url

    @Column
    private int tomato = 0; // 토마토 초기값 : 0

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<UserTomado> userTomadoList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<Memo> memoList = new ArrayList<>();

    public User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

}
