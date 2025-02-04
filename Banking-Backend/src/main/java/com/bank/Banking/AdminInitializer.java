package com.bank.Banking;

import com.bank.Banking.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserServiceImpl userService;

    public AdminInitializer(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create the admin user if not already exists
        userService.createAdminIfNotExists();
    }
}
