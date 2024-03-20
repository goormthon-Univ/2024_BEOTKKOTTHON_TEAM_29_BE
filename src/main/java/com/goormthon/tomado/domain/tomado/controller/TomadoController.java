package com.goormthon.tomado.domain.tomado.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.tomado.dto.TomadoDto;
import com.goormthon.tomado.domain.tomado.service.TomadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class TomadoController {

    private final TomadoService tomadoService;

    @GetMapping("/{tomado_id}")
    public ApiResponse<TomadoDto.Response> findById(@PathVariable Long tomado_id) {
        return tomadoService.findById(tomado_id);
    }

}
