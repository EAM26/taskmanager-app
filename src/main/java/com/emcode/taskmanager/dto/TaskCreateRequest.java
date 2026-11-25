package com.emcode.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
        @NotBlank @Size(min=1, max=100)
        String title,
        @Size(max=200)
        String description,
        @NotNull
        Long projectId

) {
}
