package com.bank.Banking.controller;

import com.bank.Banking.dto.AdminRequest;
import com.bank.Banking.dto.StaffRequest;
import com.bank.Banking.entity.User;
import com.bank.Banking.service.interf.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    // Create Admin
    @PostMapping("/create-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> createAdminUser(@Valid @RequestBody AdminRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = User.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .build();

            User createdUser = adminUserService.createAdminUser(user);

            response.put("status", "SUCCESS");
            response.put("message", "Admin user created successfully");
            response.put("data", createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    //Create Staff
    @PostMapping("/create-staff")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> createStaffUser(@Valid @RequestBody StaffRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = User.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .build();

            User createdUser = adminUserService.createStaffUser(user);

            response.put("status", "SUCCESS");
            response.put("message", "Staff user created successfully");
            response.put("data", createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
