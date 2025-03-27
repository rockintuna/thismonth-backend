package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemoResponseDto {

    private Long id;
    private String content;
    private int year;
    private int month;
    private String userName;

}
