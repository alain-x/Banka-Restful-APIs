package com.bank.Banking.controller;

import com.bank.Banking.entity.Transaction;
import com.bank.Banking.service.interf.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    // Create Debit Account
    @PostMapping("/debit/{accountId}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<Map<String, Object>> debitAccount(@PathVariable Long accountId, @RequestParam Double amount) {
        Map<String, Object> response = new HashMap<>();
        try {
            Transaction transaction = accountTransactionService.debitAccount(accountId, amount);

            response.put("status", "SUCCESS");
            response.put("message", "Account debited successfully");
            response.put("data", transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Create Credit Account
    @PostMapping("/credit/{accountId}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<Map<String, Object>> creditAccount(@PathVariable Long accountId, @RequestParam Double amount) {
        Map<String, Object> response = new HashMap<>();
        try {
            Transaction transaction = accountTransactionService.creditAccount(accountId, amount);

            response.put("status", "SUCCESS");
            response.put("message", "Account credited successfully");
            response.put("data", transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
