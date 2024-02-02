package com.example.Kiranastore.controller;

import com.example.Kiranastore.entity.Transaction;
import com.example.Kiranastore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/record")
    public ResponseEntity<Transaction> recordTransaction(@RequestParam String description,
                                                         @RequestParam double amount,
                                                         @RequestParam String currency) {
        Transaction transaction = transactionService.recordTransaction(description, amount, currency);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/daily")
    public ResponseEntity<List<Transaction>> getDailyTransactions() {
        List<Transaction> transactions = transactionService.getDailyTransactions();
        return ResponseEntity.ok(transactions);
    }
}
