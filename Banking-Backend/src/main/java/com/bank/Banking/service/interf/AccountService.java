package com.bank.Banking.service.interf;

import com.bank.Banking.entity.Account;
import com.bank.Banking.enums.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(String email, AccountType accountType);

    List<Account> getAllAccounts();

    List<Account> getAccountByEmail(String email);

    Optional<Account> getAccountById(Long id);
}
