package com.emcode.taskmanager.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
