package com.emcode.taskmanager.dto;

import com.emcode.taskmanager.model.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectPutRequest(
        @NotBlank @Size(min=1, max=50)
        String title,
        @Size(max=200)
        String description,
        @NotNull
        ProjectStatus status


) {
}
