package me.rockintuna.thismonthbe.controller;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.dto.MemoRequestDto;
import me.rockintuna.thismonthbe.dto.MemoResponseDto;
import me.rockintuna.thismonthbe.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/api/memos")
    public ResponseEntity<List<MemoResponseDto>> getMemos(
            @RequestParam Integer year,
            @RequestParam Integer month) {
        return ResponseEntity.ok(memoService.getMemos(year, month));
    }

    @PostMapping("/api/memos")
    public ResponseEntity<MemoResponseDto> getMemos(
            @RequestBody MemoRequestDto requestDto) {
        MemoResponseDto memo = memoService.createMemo(requestDto);
        URI location = URI.create("/api/memos?year=" + memo.getYear() + "&month=" + memo.getMonth());
        return ResponseEntity.created(location).body(memo);
    }

    @DeleteMapping("/api/memos/{memoId}")
    public ResponseEntity<Void> deleteMemo(
            @PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        return ResponseEntity.ok().build();
    }
}
