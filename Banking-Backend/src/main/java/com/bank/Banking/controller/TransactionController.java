package com.bank.Banking.controller;

import com.bank.Banking.entity.Transaction;
import com.bank.Banking.service.interf.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Get Transaction History
    @GetMapping("/history/{accountId}")
    public ResponseEntity<Map<String, Object>> getTransactionHistory(@PathVariable Long accountId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Transaction> transactions = transactionService.getTransactionHistory(accountId);

            response.put("status", "SUCCESS");
            response.put("message", "Transaction history retrieved successfully");
            response.put("data", transactions);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Get Specific Transaction
    @GetMapping("/{transactionId}")
    public ResponseEntity<Map<String, Object>> getSpecificTransaction(@PathVariable Long transactionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Transaction transaction = transactionService.getSpecificTransaction(transactionId);

            response.put("status", "SUCCESS");
            response.put("message", "Transaction retrieved successfully");
            response.put("data", transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
