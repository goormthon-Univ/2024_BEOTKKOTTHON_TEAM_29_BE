package com.goormthon.tomado.domain.tomado.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.common.response.SuccessMessage;
import com.goormthon.tomado.domain.tomado.dto.TomadoDto;
import com.goormthon.tomado.domain.tomado.entity.Tomado;
import com.goormthon.tomado.domain.tomado.repository.TomadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TomadoService {

    private final TomadoRepository tomadoRepository;

    @Transactional(readOnly = true)
    public ApiResponse<TomadoDto.Response> findById(Long tomadoId) {
        Tomado tomado = tomadoRepository.findById(tomadoId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.TOMADO_NOT_EXIST));
        return ApiResponse.success(SuccessMessage.TOMADO_FETCH_SUCCESS, TomadoDto.from(tomado));
    }
}
