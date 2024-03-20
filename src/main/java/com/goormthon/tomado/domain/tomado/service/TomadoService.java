package com.goormthon.tomado.domain.tomado.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.common.response.SuccessMessage;
import com.goormthon.tomado.domain.tomado.dto.TomadoDto;
import com.goormthon.tomado.domain.tomado.entity.Tomado;
import com.goormthon.tomado.domain.tomado.repository.TomadoRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.entity.UserTomado;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import com.goormthon.tomado.domain.user.repository.UserTomadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TomadoService {

    private final TomadoRepository tomadoRepository;
    private final UserTomadoRepository userTomadoRepository;
    private final UserRepository userRepository;

    // 토마두 캐릭터 개수
    private final Long TOMADO_COUNT = 4L;

    @Transactional(readOnly = true)
    public ApiResponse<TomadoDto.Response> findById(Long tomadoId) {
        Tomado tomado = tomadoRepository.findById(tomadoId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.TOMADO_NOT_EXIST));
        return ApiResponse.success(SuccessMessage.TOMADO_FETCH_SUCCESS, TomadoDto.from(tomado));
    }

    @Transactional(readOnly = true)
    public ApiResponse<TomadoDto.SimpleResponseList> findAvailableTomadoList(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));

        // 사용자가 보유한 토마두 리스트 -> tomadoIdList : id 값만 따로 빼서 저장
        List<UserTomado> userTomadoList = userTomadoRepository.findByUserId(user.getId()).orElse(new ArrayList<UserTomado>());
        List<Long> tomadoIdList = new ArrayList<Long>();
        for (UserTomado userTomado : userTomadoList) {
            tomadoIdList.add(userTomado.getTomado().getId());
        }

        // 사용자가 보유한 토마두 캐릭터 id를 담을 리스트 : tomadoHaveList
        // 사용자가 보유하지 않은 토마두 캐릭터 id를 탐을 리스트 : tomadoNotHaveList
        List<TomadoDto.SimpleResponse> tomadoHaveList = new ArrayList<>();
        List<TomadoDto.SimpleResponse> tomadoNotHaveList = new ArrayList<>();

        // 사용자가 가지고 있는 캐릭터인지 아닌지 확인
        // 보유 -> tomadoHaveList에 add | 미보유 -> tomadoHaveNotList에 add
        for (Long i = 0L; i < TOMADO_COUNT; i++) {  // TOMADO_COUNT는 관리자가 관리하기 때문에 상수로 지정
            if (tomadoIdList.contains(i)) {
                tomadoHaveList.add(new TomadoDto.SimpleResponse(i));
            } else {
                tomadoNotHaveList.add(new TomadoDto.SimpleResponse(i));
            }
        }

        return ApiResponse.success(SuccessMessage.TOMADO_FETCH_SUCCESS, new TomadoDto.SimpleResponseList(tomadoNotHaveList));

    }

}
