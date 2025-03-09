package me.rockintuna.thismonthbe.controller;

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
    public ResponseEntity<List<TransactionResponseDto>> transactions(
            @RequestParam Integer year,
            @RequestParam Integer month
    ) {
        return ResponseEntity.ok(transactionService.getTransactions(year, month));
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> transactions(
            @RequestBody TransactionRequestDto requestDto
    ) {
        transactionService.addTransactions(requestDto);
        return ResponseEntity.ok().build();
    }
}
