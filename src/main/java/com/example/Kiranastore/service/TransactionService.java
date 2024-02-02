package com.example.Kiranastore.service;

import com.example.Kiranastore.entity.Transaction;
import com.example.Kiranastore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Transactional
    public Transaction recordTransaction(String description, double amount, String currency) {
        double convertedAmount = currencyConverterService.convertAmount(amount, currency, "USD");
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(convertedAmount);
        transaction.setCurrency("USD");
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getDailyTransactions() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59).plusSeconds(59);

        return transactionRepository.findByTimestampBetween(startOfDay, endOfDay);
    }
}
