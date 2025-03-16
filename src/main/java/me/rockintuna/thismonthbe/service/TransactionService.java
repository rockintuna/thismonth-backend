package me.rockintuna.thismonthbe.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.domain.Transaction;
import me.rockintuna.thismonthbe.domain.User;
import me.rockintuna.thismonthbe.dto.TransactionResponseDto;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;
import me.rockintuna.thismonthbe.repositosy.TransactionRepository;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public List<TransactionResponseDto> getTransactions(Integer year, Integer month) {
        List<Transaction> transactionList = transactionRepository.findAllByYearAndMonth(year, month);

        return transactionList.stream().map(TransactionResponseDto::of).toList();
    }

    public TransactionResponseDto addTransactions(TransactionRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new RuntimeException("User with id " + requestDto.getUserId() + " not found")
        );

        return TransactionResponseDto.of(
                transactionRepository.save(Transaction.create(requestDto, user)));
    }

    @Transactional
    public TransactionResponseDto updateTransactions(Long transactionId, @Valid TransactionRequestDto requestDto) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(
                () -> new RuntimeException("Transaction with id " + transactionId + " not found")
        );

        transaction.update(requestDto);
        return TransactionResponseDto.of(transaction);
    }
}
