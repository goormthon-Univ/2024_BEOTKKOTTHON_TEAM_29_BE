package com.goormthon.tomado.domain.memo.controller;

import com.goormthon.tomado.common.ApiResponse;
import com.goormthon.tomado.domain.memo.dto.MemoDto;
import com.goormthon.tomado.domain.memo.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    public final MemoService memoService;

    @Operation(summary = "메모 리스트 조회")
    @GetMapping("/{user_id}")
    public ApiResponse<MemoDto.ResponseList> getMemoList(@PathVariable(name = "user_id") Long userId) {
        return memoService.getMemoList(userId);
    }

    @Operation(summary = "메모 작성")
    @PostMapping("/{user_id}")
    public ApiResponse write(@PathVariable(name = "user_id") Long userId, @RequestBody MemoDto.Write write) {
        return memoService.write(userId, write);
    }

    @Operation(summary = "메모 삭제")
    @DeleteMapping
    public ApiResponse delete(@RequestParam(name = "user") Long userId, @RequestParam("memo") Long memoId) {
        return memoService.delete(userId, memoId);
    }

}
