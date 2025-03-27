package me.rockintuna.thismonthbe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tm_memo")
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name="tx_year")
    private Integer year;
    @Column(name="tx_month")
    private Integer month;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
