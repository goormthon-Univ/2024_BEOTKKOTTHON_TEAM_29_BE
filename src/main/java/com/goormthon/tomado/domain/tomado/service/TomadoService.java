package com.goormthon.tomado.domain.tomado.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
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

import static com.goormthon.tomado.common.response.ErrorMessage.*;
import static com.goormthon.tomado.common.response.SuccessMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class TomadoService {

    // 토마두 캐릭터 개수
    private static final Long TOMADO_COUNT = 9L;
    private final TomadoRepository tomadoRepository;
    private final UserTomadoRepository userTomadoRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ApiResponse<TomadoDto.Response> getTomadoInfo(Long tomadoId) {
        Tomado tomado = getTomado(tomadoId);
        return ApiResponse.success(TOMADO_FETCH_SUCCESS, TomadoDto.from(tomado));
    }

    @Transactional(readOnly = true)
    public ApiResponse<TomadoDto.ResponseList> getTomadoList(Long userId) {
        User user = getUser(userId);

        // 사용자가 보유한 토마두 리스트 -> tomadoIdList : id 값만 따로 빼서 저장
        List<Long> tomadoIdList = getUserTomadoIdList(user);

        // 사용자가 보유한 토마두 캐릭터 id를 담을 리스트 : tomadoHaveList
        // 사용자가 보유하지 않은 토마두 캐릭터 id를 탐을 리스트 : tomadoNotHaveList
        List<TomadoDto.Response> tomadoHaveList = new ArrayList<>();
        List<TomadoDto.Response> tomadoNotHaveList = new ArrayList<>();

        // 사용자가 가지고 있는 캐릭터인지 아닌지 확인
        // 보유 -> tomadoHaveList에 add | 미보유 -> tomadoHaveNotList에 add
        for (Long i = 1L; i <= TOMADO_COUNT; i++) {  // TOMADO_COUNT는 관리자가 관리하기 때문에 상수로 지정
            if (tomadoIdList.contains(i)) {
                tomadoHaveList.add(TomadoDto.from(getTomado(i)));
            } else {
                tomadoNotHaveList.add(TomadoDto.from(getTomado(i)));
            }
        }

        return ApiResponse.success(TOMADO_FETCH_SUCCESS, new TomadoDto.ResponseList(tomadoHaveList, tomadoNotHaveList));
    }

    private List<Long> getUserTomadoIdList(User user) {
        List<Long> tomadoIdList = new ArrayList<Long>();
        for (UserTomado userTomado : user.getUserTomadoList()) {
            tomadoIdList.add(userTomado.getTomado().getId());
        }
        return tomadoIdList;
    }

    public ApiResponse buyTomado(Long userId, Long tomadoId) {
        // 회원 정보 - 보유 토마량 확인
        User user = getUser(userId);

        // 토마두 정보 - 구매 토마량 확인
        Tomado tomado = getTomado(tomadoId);

        // 구매한 토마두인지 확인
        checkUserHaveTomado(user, tomado);
        // 회원이 충분한 토마를 보유했는지 확인
        checkUserHaveEnoughToma(user.getTomato() < tomado.getTomato(), USER_TOMATO_NOT_ENOUGH);

        userTomadoRepository.save(new UserTomado(user, tomado));
        user.addToma(-tomado.getTomato());
        userRepository.save(user);

        return ApiResponse.success(TOMADO_BUY_SUCCESS);
    }

    private void checkUserHaveTomado(User user, Tomado tomado) {
        for (UserTomado userTomado : user.getUserTomadoList()) {
            checkUserHaveEnoughToma(userTomado.getTomado().getId().equals(tomado.getId()), USER_TOMADO_ALREADY_EXIST);
        }
    }

    private void checkUserHaveEnoughToma(boolean user, ErrorMessage userTomatoNotEnough) {
        if (user) {
            throw new BadRequestException(userTomatoNotEnough);
        }
    }

    private Tomado getTomado(Long tomadoId) {
        return tomadoRepository.findById(tomadoId).orElseThrow(() -> new NotFoundException(TOMADO_NOT_EXIST));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
    }

}
