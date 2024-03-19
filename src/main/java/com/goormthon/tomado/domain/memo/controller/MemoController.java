package com.goormthon.tomado.domain.memo.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.memo.dto.MemoDto;
import com.goormthon.tomado.domain.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemoController {

    public final MemoService memoService;

    @PostMapping("/memos/{user_id}")
    public ApiResponse write(@PathVariable Long user_id, @RequestBody MemoDto.Write write) {
        return memoService.write(user_id, write);
    }

    @DeleteMapping("/memos")
    public ApiResponse delete(@RequestParam(name = "user") Long user_id, @RequestParam("memo") Long memo_id) {
        return memoService.delete(user_id, memo_id);
    }

}
