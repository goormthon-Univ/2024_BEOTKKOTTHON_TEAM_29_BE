package com.goormthon.tomado.domain.tomado.service;

import com.goormthon.tomado.domain.tomado.repository.TomadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TomadoService {

    private final TomadoRepository tomadoRepository;
    
}
