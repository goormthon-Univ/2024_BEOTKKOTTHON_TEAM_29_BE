package com.goormthon.tomado.domain.memo.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.ErrorMessage;
import com.goormthon.tomado.common.response.SuccessMessage;
import com.goormthon.tomado.domain.memo.dto.MemoDto;
import com.goormthon.tomado.domain.memo.entity.Memo;
import com.goormthon.tomado.domain.memo.repository.MemoRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    @Transactional
    public ApiResponse write(Long userId, MemoDto.Write write) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));

        memoRepository.save(new Memo(user, write.getContent()));
        return ApiResponse.success(SuccessMessage.MEMO_CREATE_SUCCESS);

    }

    @Transactional
    public ApiResponse delete(Long userId, Long memoId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_EXIST));

        boolean existMemo = false;
        for (Memo memo : user.getMemoList()) {
            if (memo.getId().equals(memoId)) {
                existMemo = true;
                break;
            }
        }

        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMO_NOT_EXIST));
        if (existMemo) {
            memoRepository.delete(memo);
        } else {
            throw new BadRequestException(ErrorMessage.USER_NOT_HAVE_MEMO);
        }

        return ApiResponse.success(SuccessMessage.MEMO_DELETE_SUCCESS);

    }
}
