package com.goormthon.tomado.domain.tomado.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Tomado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Column
    private String name;

    @Column
    private String content;

    @Column
    private int tomato;

}
