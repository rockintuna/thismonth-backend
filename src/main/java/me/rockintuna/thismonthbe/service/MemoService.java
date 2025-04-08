package me.rockintuna.thismonthbe.service;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.domain.Memo;
import me.rockintuna.thismonthbe.domain.User;
import me.rockintuna.thismonthbe.dto.MemoRequestDto;
import me.rockintuna.thismonthbe.dto.MemoResponseDto;
import me.rockintuna.thismonthbe.exceptions.UserNotFoundException;
import me.rockintuna.thismonthbe.repositosy.MemoRepository;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public List<MemoResponseDto> getMemos(int year, int month) {
        return memoRepository.findAllByYearAndMonth(year, month)
                .stream().map(MemoResponseDto::of)
                .toList();
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return MemoResponseDto.of(memoRepository.save(Memo.create(requestDto, user)));
    }

    public void deleteMemo(Long memoId) {
        memoRepository.deleteById(memoId);
    }
}
