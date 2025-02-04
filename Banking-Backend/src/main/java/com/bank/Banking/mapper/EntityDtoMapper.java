package com.bank.Banking.mapper;

import com.bank.Banking.dto.LoginRequest;
import com.bank.Banking.dto.Response;
import com.bank.Banking.dto.UserDto;
import com.bank.Banking.entity.User;
import com.bank.Banking.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    // Convert User entity to UserDto (basic version)
    public UserDto mapUserToDtoBasic(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(UserRole.valueOf(user.getRole().name())); // Assuming the role is a string in the DTO
        userDto.setName(user.getName());
        userDto.setActive(user.isActive()); // Ensure active status is included if needed

        return userDto;
    }

    // Convert UserDto to User entity
    public User mapDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword()); // Note: Password should be encoded when saving
        user.setRole(userDto.getRole()); // Assuming role is passed as a string in the DTO
        user.setActive(userDto.isActive()); // Ensure active status is set if needed

        return user;
    }

    // Convert User entity to UserDto with all fields (if needed)
    public UserDto mapUserToDtoFull(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(UserRole.valueOf(user.getRole().name())); // Assuming the role is a string in the DTO
        userDto.setName(user.getName());
        userDto.setActive(user.isActive());
        userDto.setPassword(user.getPassword()); // You may or may not want to include the password in DTOs

        return userDto;
    }

    // Convert LoginRequest DTO to User entity (for login)
    public User mapLoginRequestToUser(LoginRequest loginRequest) {
        if (loginRequest == null) {
            return null;
        }

        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword()); // Note: Password should be encoded when saving
        return user;
    }

    // Convert Response DTO to User entity if needed
    public Response mapUserToResponse(User user) {
        UserDto userDto = mapUserToDtoBasic(user); // You can choose the appropriate mapping here

        return Response.builder()
                .status(200)
                .message("User found")

                .build();
    }
}
