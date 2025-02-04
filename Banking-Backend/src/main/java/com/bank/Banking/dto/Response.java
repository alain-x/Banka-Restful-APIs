package com.bank.Banking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.bank.Banking.entity.Account;
import com.bank.Banking.entity.Transaction;
import com.bank.Banking.entity.User;
import com.bank.Banking.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private UserRole role;
    private String expirationTime;

    private int totalPage;
    private long totalElement;

    // Return the entities directly
    private Account account;
    private List<Account> accountList;

    private Transaction transaction;
    private List<Transaction> transactionList;

    private User user;
    private List<User> userList;



    // Helper method to build response with pagination info
    public static Response buildWithPagination(int status, String message, int totalPage, long totalElement) {
        return Response.builder()
                .status(status)
                .message(message)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .build();
    }

    // Helper method to create a response for account-related actions
    public static Response accountActionSuccess(String message, Account account) {
        return Response.builder()
                .status(200)
                .message(message)
                .account(account)
                .build();
    }

    // Helper method to create a response for transactions
    public static Response transactionActionSuccess(String message, Transaction transaction) {
        return Response.builder()
                .status(200)
                .message(message)
                .transaction(transaction)
                .build();
    }

    // Helper method for getting list of accounts (with pagination)
    public static Response accountListWithPagination(int status, String message, List<Account> accountList, int totalPage, long totalElement) {
        return Response.builder()
                .status(status)
                .message(message)
                .accountList(accountList)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .build();
    }

    // Helper method for getting list of transactions (with pagination)
    public static Response transactionListWithPagination(int status, String message, List<Transaction> transactionList, int totalPage, long totalElement) {
        return Response.builder()
                .status(status)
                .message(message)
                .transactionList(transactionList)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .build();
    }
}
