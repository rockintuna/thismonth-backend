package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.rockintuna.thismonthbe.domain.Memo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemoResponseDto {
    private Long id;
    private String content;
    private int year;
    private int month;
    private String userName;

    public static MemoResponseDto of(Memo memo) {
        return new MemoResponseDto(
                memo.getId(),
                memo.getContent(),
                memo.getYear(),
                memo.getMonth(),
                memo.getUser().getUsername()
        );
    }
}
