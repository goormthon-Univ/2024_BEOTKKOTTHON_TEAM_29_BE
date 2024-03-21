package com.goormthon.tomado.domain.club.controller;

import com.goormthon.tomado.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
}
