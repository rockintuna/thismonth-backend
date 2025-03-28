package me.rockintuna.thismonthbe.service;

import me.rockintuna.thismonthbe.domain.Memo;
import me.rockintuna.thismonthbe.domain.User;
import me.rockintuna.thismonthbe.dto.MemoRequestDto;
import me.rockintuna.thismonthbe.dto.MemoResponseDto;
import me.rockintuna.thismonthbe.repositosy.MemoRepository;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemoServiceTest {

    @InjectMocks
    private MemoService memoService;
    @Mock
    private MemoRepository memoRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void getMemos() {
        //given
        int year = 2020;
        int month = 12;

        User user1 = new User(1L, "tester1");
        User user2 = new User(2L, "tester2");

        List<Memo> list = new ArrayList<>();
        Memo test = new Memo(1L, "test", year, month, user1);
        list.add(test);
        list.add(new Memo(2L, "test2", year, month, user1));
        list.add(new Memo(3L, "test3", year, month, user2));
        list.add(new Memo(4L, "test4", year, month, user2));

        when(memoRepository.findAllByYearAndMonth(year, month))
                .thenReturn(list);

        //when
        List<MemoResponseDto> memos = memoService.getMemos(year, month);

        //then
        assertThat(memos).isNotNull();
        assertThat(memos).isNotEmpty();
        assertThat(memos.size()).isEqualTo(list.size());
        assertThat(memos.getFirst().getUserName()).isEqualTo(test.getUser().getUsername());
        assertThat(memos.getFirst().getYear()).isEqualTo(year);
        assertThat(memos.getFirst().getMonth()).isEqualTo(month);
    }

    @Test
    void createMemo() {
        int year = 2020;
        int month = 12;
        User user = new User(1L, "tester1");
        MemoRequestDto requestDto = new MemoRequestDto(
                year, month, "test", user.getId()
        );

        when(userRepository.findById(requestDto.getUserId()))
                .thenReturn(Optional.of(user));
        when(memoRepository.save(any(Memo.class)))
                .thenReturn(Memo.create(requestDto, user));

        MemoResponseDto memo = memoService.createMemo(requestDto);

        verify(memoRepository, times(1)).save(any(Memo.class));

        assertThat(memo).isNotNull();
        assertThat(memo.getYear()).isEqualTo(year);
        assertThat(memo.getMonth()).isEqualTo(month);
    }
}