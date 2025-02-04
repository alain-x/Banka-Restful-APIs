package com.bank.Banking.service.impl;

import com.bank.Banking.entity.User;
import com.bank.Banking.enums.UserRole;
import com.bank.Banking.repository.UserRepo;
import com.bank.Banking.service.interf.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepo userRepo;


    private final PasswordEncoder passwordEncoder;


    @Override
    public User createAdminUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ADMIN); // Ensure the role is set
        return userRepo.save(user);
    }

    @Override
    public User createStaffUser(User user1) {
        String encodedPassword = passwordEncoder.encode(user1.getPassword());
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        user1.setRole(UserRole.STAFF);

        return userRepo.save(user1);
    }
}

