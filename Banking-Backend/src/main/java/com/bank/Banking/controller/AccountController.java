package com.bank.Banking.controller;

import com.bank.Banking.entity.Account;
import com.bank.Banking.enums.AccountType;
import com.bank.Banking.service.interf.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request) {
        try {
            Account account = accountService.createAccount(request.getEmail(), request.getAccountType());
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Account creation failed: " + e.getMessage());
        }
    }

    // View all user accounts - Admin/STAFF only
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // View specific user account by email - Admin/STAFF only
    @GetMapping("email/{email}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public Optional<Account> getAccountByEmail(@PathVariable String email) {
        return accountService.getAccountByEmail(email);
    }

    // View specific user account Admin/STAFF only
    @GetMapping("id/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STAFF')")
    public Optional<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
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
