package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemoRequestDto {
    private Integer year;
    private Integer month;
    private String content;
    private Long userId;
}
