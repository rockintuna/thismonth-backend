package me.rockintuna.thismonthbe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.dto.TransactionResponseDto;
import me.rockintuna.thismonthbe.dto.TransactionRequestDto;
import me.rockintuna.thismonthbe.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(
            @RequestParam Integer year,
            @RequestParam Integer month
    ) {
        return ResponseEntity.ok(transactionService.getTransactions(year, month));
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDto> addTransactions(
            @RequestBody @Valid TransactionRequestDto requestDto
    ) {
        return ResponseEntity.ok(transactionService.addTransactions(requestDto));
    }

    @PatchMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionResponseDto> updateTransactions(
            @PathVariable Long transactionId,
            @RequestBody @Valid TransactionRequestDto requestDto
    ) {
        return ResponseEntity.ok(transactionService.updateTransactions(transactionId, requestDto));
    }

    @DeleteMapping("/transactions/{transactionId}")
    public ResponseEntity<Void> deleteTransactions(
            @PathVariable Long transactionId
    ) {
        transactionService.deleteTransactions(transactionId);
        return ResponseEntity.ok().build();
    }
}
