package com.bank.Banking.controller;

import com.bank.Banking.service.interf.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/activate/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void activateAccount(@PathVariable Long userId) {
        adminService.activateAccount(userId);
    }

    @PostMapping("/deactivate/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deactivateAccount(@PathVariable Long userId) {
        adminService.deactivateAccount(userId);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAccount(@PathVariable Long userId) {
        adminService.deleteUserAccount(userId);
    }
}
