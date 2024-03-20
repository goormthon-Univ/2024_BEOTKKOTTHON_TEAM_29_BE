package com.goormthon.tomado.domain.memo.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.memo.dto.MemoDto;
import com.goormthon.tomado.domain.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    public final MemoService memoService;

    @GetMapping("/{user_id}")
    public ApiResponse<MemoDto.ResponseList> getMemoList(@PathVariable Long user_id) {
        return memoService.getMemoList(user_id);
    }

    @PostMapping("/{user_id}")
    public ApiResponse write(@PathVariable Long user_id, @RequestBody MemoDto.Write write) {
        return memoService.write(user_id, write);
    }

    @DeleteMapping("")
    public ApiResponse delete(@RequestParam(name = "user") Long user_id, @RequestParam("memo") Long memo_id) {
        return memoService.delete(user_id, memo_id);
    }

}
