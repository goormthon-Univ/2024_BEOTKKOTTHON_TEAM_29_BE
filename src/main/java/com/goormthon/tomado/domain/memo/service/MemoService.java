package com.goormthon.tomado.domain.memo.service;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.common.exception.BadRequestException;
import com.goormthon.tomado.common.exception.NotFoundException;
import com.goormthon.tomado.common.response.SuccessMessage;
import com.goormthon.tomado.domain.memo.dto.MemoDto;
import com.goormthon.tomado.domain.memo.entity.Memo;
import com.goormthon.tomado.domain.memo.repository.MemoRepository;
import com.goormthon.tomado.domain.user.entity.User;
import com.goormthon.tomado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.goormthon.tomado.common.response.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoService {

    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public ApiResponse<MemoDto.ResponseList> getMemoList(Long userId) {
        User user = getUser(userId);
        return ApiResponse.success(SuccessMessage.MEMO_LIST_FETCH_SUCCESS, MemoDto.from(user.getMemoList()));
    }

    public ApiResponse write(Long userId, MemoDto.Write write) {
        memoRepository.save(new Memo(getUser(userId), write.getContent()));
        return ApiResponse.success(SuccessMessage.MEMO_CREATE_SUCCESS);
    }

    public ApiResponse delete(Long userId, Long memoId) {
        User user = getUser(userId);

        boolean existMemo = false;
        for (Memo memo : user.getMemoList()) {
            if (memo.getId().equals(memoId)) {
                existMemo = true;
                break;
            }
        }
        

        if (existMemo) {
            memoRepository.delete(getMemo(memoId));
        } else {
            throw new BadRequestException(USER_NOT_HAVE_MEMO);
        }

        return ApiResponse.success(SuccessMessage.MEMO_DELETE_SUCCESS);
    }

    private Memo getMemo(Long memoId) {
        return memoRepository.findById(memoId).orElseThrow(() -> new NotFoundException(MEMO_NOT_EXIST));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_EXIST));
    }

}
