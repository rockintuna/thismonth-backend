package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionRequestDto {
    private String title;
    private Long amount;
    private Integer year;
    private Integer month;
    private Long userId;
}
