package com.bank.Banking.service.impl;


import com.bank.Banking.entity.Account;
import com.bank.Banking.entity.Transaction;
import com.bank.Banking.enums.TransactionType;
import com.bank.Banking.repository.AccountRepo;
import com.bank.Banking.repository.TransactionRepo;
import com.bank.Banking.service.interf.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    public Transaction debitAccount(Long accountId, Double amount) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEBIT)
                .transactionDate(java.time.LocalDateTime.now())
                .description("Debit transaction")
                .build();

        return transactionRepo.save(transaction);
    }

    public Transaction creditAccount(Long accountId, Double amount) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.CREDIT)
                .transactionDate(java.time.LocalDateTime.now())
                .description("Credit transaction")
                .build();

        return transactionRepo.save(transaction);
    }
}