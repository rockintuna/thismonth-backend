package me.rockintuna.thismonthbe.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionRequestDto {
    @Length(min = 1, max = 50)
    private String title;
    private Long amount;
    @Min(1)
    private Integer year;
    @Min(1)
    @Max(12)
    private Integer month;
    @NotNull
    private Long userId;
}
