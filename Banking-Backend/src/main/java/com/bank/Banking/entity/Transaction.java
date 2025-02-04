package com.bank.Banking.entity;

import com.bank.Banking.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;  // The associated account for the transaction

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate = LocalDateTime.now();  // Automatically set to current time

    @Enumerated(EnumType.STRING)  // This ensures the enum is stored as a string in the DB
    private TransactionType type; // Credit or Debit

    private String description;

    // Add other relevant fields like transactionId if necessary
}
