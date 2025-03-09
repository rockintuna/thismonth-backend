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
@Table(name="tm_tx")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Long amount;
    @Column(name="tx_year")
    private Integer year;
    @Column(name="tx_month")
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
