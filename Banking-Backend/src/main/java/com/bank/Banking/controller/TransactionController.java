package com.bank.Banking.controller;

import com.bank.Banking.entity.Transaction;
import com.bank.Banking.service.interf.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/history/{accountId}")
    public List<Transaction> getTransactionHistory(@PathVariable Long accountId) {
        return transactionService.getTransactionHistory(accountId);
    }

    @GetMapping("/{transactionId}")
    public Transaction getSpecificTransaction(@PathVariable Long transactionId) {
        return transactionService.getSpecificTransaction(transactionId);
    }
}
