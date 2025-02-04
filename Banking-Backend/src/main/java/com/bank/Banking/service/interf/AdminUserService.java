package com.bank.Banking.service.interf;

import com.bank.Banking.entity.User;

public interface AdminUserService {
 

    User createAdminUser(User user);

    User createStaffUser(User user1);
}
