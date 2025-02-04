package com.bank.Banking.service.interf;

import com.bank.Banking.dto.LoginRequest;
import com.bank.Banking.dto.Response;
import com.bank.Banking.dto.UserDto;

public interface UserService {
    Response registerUser(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);


}
