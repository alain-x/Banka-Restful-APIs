package com.bank.Banking.controller;
import com.bank.Banking.dto.AdminRequest;
import com.bank.Banking.dto.StaffRequest;
import com.bank.Banking.entity.User;
import com.bank.Banking.service.interf.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping("/create-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User createAdminUser(@Valid @RequestBody AdminRequest request) {
        // Ensure all required fields are set
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName()) // Ensure 'name' is set
                .phoneNumber(request.getPhoneNumber()) // Ensure 'phoneNumber' is set
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        return adminUserService.createAdminUser(user);
    }






    @PostMapping("/create-staff")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User createStaffUser(@Valid @RequestBody StaffRequest request) {
        User user1 = User.builder()
                .email(request.getEmail())
                .name(request.getName()) // Ensure 'name' is set
                .phoneNumber(request.getPhoneNumber()) // Ensure 'phoneNumber' is set
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        return adminUserService.createStaffUser(user1);
    }
}
