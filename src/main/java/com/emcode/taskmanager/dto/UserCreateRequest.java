package com.emcode.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank
        @Size(max=20, min=3)
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
