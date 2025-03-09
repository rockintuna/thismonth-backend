package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionResponseDto {
    private String title;
    private Long amount;
    private String userName;
}
