package com.goormthon.tomado.domain.tomado.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.tomado.dto.TomadoDto;
import com.goormthon.tomado.domain.tomado.service.TomadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class TomadoController {

    private final TomadoService tomadoService;

    @Operation(summary = "캐릭터 정보 조회")
    @GetMapping("/{tomado_id}")
    public ApiResponse<TomadoDto.Response> getTomadoInfo(@PathVariable(name = "tomado_id") Long tomadoId) {
        return tomadoService.getTomadoInfo(tomadoId);
    }

    @Operation(summary = "상점에서 캐릭터 불러오기")
    @GetMapping
    public ApiResponse<TomadoDto.ResponseList> getTomadoList(@RequestParam Long user) {
        return tomadoService.getTomadoList(user);
    }

    @Operation(summary = "캐릭터 구입")
    @PostMapping
    public ApiResponse buyTomado(@RequestParam(name = "user") Long user_id, @RequestParam(name = "tomado") Long tomado_id) {
        return tomadoService.buyTomado(user_id, tomado_id);
    }

}
