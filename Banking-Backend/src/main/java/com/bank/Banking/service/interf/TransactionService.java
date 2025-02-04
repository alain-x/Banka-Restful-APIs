package com.bank.Banking.service.interf;

import com.bank.Banking.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionHistory(Long accountId);

    Transaction getSpecificTransaction(Long transactionId);
}
