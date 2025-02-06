package com.bank.Banking.controller;

import com.bank.Banking.entity.Account;
import com.bank.Banking.enums.AccountType;
import com.bank.Banking.service.interf.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody AccountRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Account account = accountService.createAccount(request.getEmail(), request.getAccountType());
            response.put("status", "SUCCESS");
            response.put("message", "Account created successfully");
            response.put("data", account);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    // View all user accounts - Admin/STAFF only
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public ResponseEntity<Map<String, Object>> getAllAccounts() {
        Map<String, Object> response = new HashMap<>();
        List<Account> accounts = accountService.getAllAccounts();

        response.put("status", "SUCCESS");
        response.put("message", "Accounts retrieved successfully");
        response.put("data", accounts);
        return ResponseEntity.ok(response);
    }
    // View specific user account by email - Admin/STAFF only
    @GetMapping("email/{email}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public ResponseEntity<Map<String, Object>> getAccountByEmail(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        Optional<Account> account = accountService.getAccountByEmail(email);

        if (account.isPresent()) {
            response.put("status", "SUCCESS");
            response.put("message", "Account found");
            response.put("data", account);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "ERROR");
            response.put("message", "Account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // View specific user account Admin/STAFF only
    @GetMapping("id/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Account> account = accountService.getAccountById(id);

        if (account.isPresent()) {
            response.put("status", "SUCCESS");
            response.put("message", "Account found");
            response.put("data", account);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "ERROR");
            response.put("message", "Account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public static class AccountRequest {
        private String email;
        private AccountType accountType;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public AccountType getAccountType() {
            return accountType;
        }

        public void setAccountType(AccountType accountType) {
            this.accountType = accountType;
        }
    }
}
