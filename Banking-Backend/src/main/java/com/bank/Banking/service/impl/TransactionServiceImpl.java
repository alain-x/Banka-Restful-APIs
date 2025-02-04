package com.bank.Banking.service.impl;

import com.bank.Banking.entity.Transaction;
import com.bank.Banking.repository.TransactionRepo;
import com.bank.Banking.service.interf.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TransactionServiceImpl implements TransactionService {

        private final TransactionRepo transactionRepo;

        public List<Transaction> getTransactionHistory(Long accountId) {
            return transactionRepo.findByAccountId(accountId);
        }

        public Transaction getSpecificTransaction(Long transactionId) {
            return transactionRepo.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction not found"));
        }
    }