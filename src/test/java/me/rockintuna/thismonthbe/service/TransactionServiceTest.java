package me.rockintuna.thismonthbe.service;

import me.rockintuna.thismonthbe.domain.Transaction;
import me.rockintuna.thismonthbe.domain.User;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;
import me.rockintuna.thismonthbe.dto.TransactionResponseDto;
import me.rockintuna.thismonthbe.repositosy.TransactionRepository;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void testGetTransactions() {
        //given
        int year = 2025;
        int month = 1;

        User user1 = new User(1L, "tester1");
        User user2 = new User(2L, "tester2");

        List<Transaction> list = new ArrayList<>();
        Transaction transaction = new Transaction(1L, "월급", 1000000L, year, month, user1);
        list.add(transaction);
        list.add(new Transaction(2L, "월급", 1000000L, year, month, user2));
        list.add(new Transaction(3L, "교통비", -100000L, year, month, user1));
        list.add(new Transaction(4L, "통신비", -50000L, year, month, user1));

        when(transactionRepository.findAllByYearAndMonth(year, month)).thenReturn(list);

        List<TransactionResponseDto> transactions = transactionService.getTransactions(year, month);

        assertThat(transactions).isNotNull();
        assertThat(transactions).isNotEmpty();
        assertThat(transactions.size()).isEqualTo(list.size());
        assertThat(transactions.getFirst().getUserName()).isEqualTo(user1.getUsername());
        assertThat(transactions.getFirst().getTitle()).isEqualTo(transaction.getTitle());
        assertThat(transactions.getFirst().getAmount()).isEqualTo(transaction.getAmount());
        assertThat(transactions.get(1).getUserName()).isEqualTo(user2.getUsername());
    }

    @Test
    void testAddTransaction() {
        //given
        int year = 2025;
        int month = 1;

        User user1 = new User(1L, "tester1");

        TransactionRequestDto requestDto = new TransactionRequestDto(
                "월급", 1000000L, year, month, user1.getId()
        );
        Transaction transaction = new Transaction(1L, "월급", 1000000L, year, month, user1);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        TransactionResponseDto transactionResponseDto = transactionService.addTransactions(requestDto);

        assertThat(transactionResponseDto).isNotNull();
        assertThat(transactionResponseDto.getUserName()).isEqualTo(user1.getUsername());
        assertThat(transactionResponseDto.getTitle()).isEqualTo(transaction.getTitle());
        assertThat(transactionResponseDto.getAmount()).isEqualTo(transaction.getAmount());
    }

    @Test
    void testUpdateTransaction() {
        //given
        int year = 2025;
        int month = 1;

        User user1 = new User(1L, "tester1");

        TransactionRequestDto requestDto = new TransactionRequestDto(
                "월급수정", 2000000L, year, month, user1.getId()
        );
        Transaction transaction = new Transaction(1L, "월급", 1000000L, year, month, user1);

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        TransactionResponseDto transactionResponseDto = transactionService.updateTransactions(1L, requestDto);

        assertThat(transactionResponseDto).isNotNull();
        assertThat(transactionResponseDto.getUserName()).isEqualTo(user1.getUsername());
        assertThat(transactionResponseDto.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(transactionResponseDto.getAmount()).isEqualTo(requestDto.getAmount());
    }

    @Test
    void testUpdateTransactionNotFound() {
        //given
        int year = 2025;
        int month = 1;

        User user1 = new User(1L, "tester1");

        TransactionRequestDto requestDto = new TransactionRequestDto(
                "월급수정", 2000000L, year, month, user1.getId()
        );
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> transactionService.updateTransactions(1L, requestDto));
    }

    @Test
    void testDeleteTransaction() {
        //given
        int year = 2025;
        int month = 1;

        User user1 = new User(1L, "tester1");

        Transaction transaction = new Transaction(1L, "월급", 1000000L, year, month, user1);

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        transactionService.deleteTransactions(1L);

        verify(transactionRepository, times(1)).delete(any(Transaction.class));
    }
}