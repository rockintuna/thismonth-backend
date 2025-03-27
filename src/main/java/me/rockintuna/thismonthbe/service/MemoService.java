package me.rockintuna.thismonthbe.service;

import me.rockintuna.thismonthbe.dto.MemoRequestDto;
import me.rockintuna.thismonthbe.dto.MemoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    public List<MemoResponseDto> getMemos(int year, int month) {
        return null;
    }

    public MemoResponseDto createMemo(MemoRequestDto any) {
        return null;
    }
}
