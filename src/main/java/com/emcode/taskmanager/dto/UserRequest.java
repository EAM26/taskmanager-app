package com.emcode.taskmanager.dto;

public record UserRequest(
        String username,
        String email,
        String password
) {
}
