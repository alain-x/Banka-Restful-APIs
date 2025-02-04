package com.bank.Banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bank.Banking.enums.AccountType;
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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;  // The associated user for the account

    @Column(unique = true)
    private String accountNumber;  // Unique account number

    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;  // The type of account (e.g., savings, checking)

    private boolean isActive;  // Whether the account is active or not

    private LocalDateTime createdAt = LocalDateTime.now();  // Automatically set to current time

    // Add other fields as needed
}
