package com.bank.Banking.controller;

import com.bank.Banking.entity.Transaction;
import com.bank.Banking.service.interf.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    @PostMapping("/debit/{accountId}")
    @PreAuthorize("hasAuthority('STAFF')")
    public Transaction debitAccount(@PathVariable Long accountId, @RequestParam Double amount) {
        return accountTransactionService.debitAccount(accountId, amount);
    }

    @PostMapping("/credit/{accountId}")
    @PreAuthorize("hasAuthority('STAFF')")
    public Transaction creditAccount(@PathVariable Long accountId, @RequestParam Double amount) {
        return accountTransactionService.creditAccount(accountId, amount);
    }
}
