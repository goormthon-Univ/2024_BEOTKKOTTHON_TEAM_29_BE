package com.goormthon.tomado.domain.user.controller;

import com.goormthon.tomado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
}
