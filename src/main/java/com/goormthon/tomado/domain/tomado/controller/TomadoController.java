package com.goormthon.tomado.domain.tomado.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.tomado.dto.TomadoDto;
import com.goormthon.tomado.domain.tomado.service.TomadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class TomadoController {

    private final TomadoService tomadoService;

    @GetMapping("/{tomado_id}")
    public ApiResponse<TomadoDto.Response> findById(@PathVariable Long tomado_id) {
        return tomadoService.findById(tomado_id);
    }

    @GetMapping("")
    public ApiResponse<TomadoDto.ResponseList> findAvailableTomadoList(@RequestParam Long user) {
        return tomadoService.findAvailableTomadoList(user);
    }

    @PostMapping
    public ApiResponse buyTomado(@RequestParam(name = "user") Long user_id, @RequestParam(name = "tomado") Long tomado_id) {
        return tomadoService.buyTomado(user_id, tomado_id);
    }

}
