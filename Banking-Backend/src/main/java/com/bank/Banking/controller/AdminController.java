package com.bank.Banking.controller;

import com.bank.Banking.service.interf.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/activate/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> activateAccount(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.activateAccount(userId);
            response.put("status", "SUCCESS");
            response.put("message", "Account activated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/deactivate/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> deactivateAccount(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.deactivateAccount(userId);
            response.put("status", "SUCCESS");
            response.put("message", "Account deactivated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // ✅ Delete Account - Returns OK or BAD_REQUEST
    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> deleteAccount(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.deleteUserAccount(userId);
            response.put("status", "SUCCESS");
            response.put("message", "Account deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}