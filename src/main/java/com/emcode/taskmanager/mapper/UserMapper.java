package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.UserRequest;
import com.emcode.taskmanager.dto.UserResponse;
import com.emcode.taskmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return user;
    }
}
