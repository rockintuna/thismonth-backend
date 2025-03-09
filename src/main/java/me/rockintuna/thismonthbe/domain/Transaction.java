package me.rockintuna.thismonthbe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Long amount;
    private Integer year;
    private Integer month;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Transaction create(TransactionRequestDto requestDto, User user) {
        return new Transaction(
                null,
                requestDto.getTitle(),
                requestDto.getAmount(),
                requestDto.getYear(),
                requestDto.getMonth(),
                user
        );
    }
}
