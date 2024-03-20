package com.goormthon.tomado.domain.tomado.controller;

import com.goormthon.tomado.domain.tomado.service.TomadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class TomadoController {

    private final TomadoService tomadoService;

}
