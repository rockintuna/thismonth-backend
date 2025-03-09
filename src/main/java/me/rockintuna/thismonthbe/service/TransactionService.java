package me.rockintuna.thismonthbe.service;

import me.rockintuna.thismonthbe.dto.TransactionResponseDto;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    public List<TransactionResponseDto> getTransactions(Integer year, Integer month) {
        return null;
    }

    public void addTransactions(TransactionRequestDto requestDto) {
    }
}
