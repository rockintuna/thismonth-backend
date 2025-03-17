package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.rockintuna.thismonthbe.domain.Transaction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TransactionResponseDto {
    private Long id;
    private String title;
    private Long amount;
    private String userName;

    public static TransactionResponseDto of(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .title(transaction.getTitle())
                .amount(transaction.getAmount())
                .userName(transaction.getUser().getUsername())
                .build();
    }
}
