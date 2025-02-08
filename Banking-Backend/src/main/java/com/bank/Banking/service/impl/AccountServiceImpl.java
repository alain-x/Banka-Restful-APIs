package com.bank.Banking.service.impl;

import com.bank.Banking.entity.Account;
import com.bank.Banking.entity.User;
import com.bank.Banking.enums.AccountType;
import com.bank.Banking.repository.AccountRepo;
import com.bank.Banking.repository.UserRepo;
import com.bank.Banking.service.interf.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    public Account createAccount(String email, AccountType accountType) {
        log.info("Looking for user with email: {}", email);

        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            log.error("User not found with email: {}", email);
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        Account newAccount = Account.builder()
                .user(user)
                .accountType(accountType)
                .balance(0.0)
                .isActive(true)
                .accountNumber(generateAccountNumber())
                .build();

        return accountRepo.save(newAccount);
    }

    @Override
    public List<Account> getAllAccounts() {
        log.info("Get all accounts...");
        return accountRepo.findAll();
    }

    @Override
    public List<Account> getAccountByEmail(String email) {
        log.info("Get account for email: {}", email);
        return accountRepo.findByUserEmail(email);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        log.info("Get account for ID: {}", id);
        return accountRepo.findById(id);
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }
}
