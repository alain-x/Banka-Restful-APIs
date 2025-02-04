package com.bank.Banking.service.interf;

import com.bank.Banking.entity.Transaction;

public interface AccountTransactionService {
    Transaction debitAccount(Long accountId, Double amount);

    Transaction creditAccount(Long accountId, Double amount);
}
