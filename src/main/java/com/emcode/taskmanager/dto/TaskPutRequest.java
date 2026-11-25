package com.emcode.taskmanager.dto;

import com.emcode.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskPutRequest(
        @NotBlank @Size(min=1, max=100)
        String title,
        @Size(max=200)
        String description,
        @NotNull
        TaskStatus status,
        @NotNull
        boolean isArchived,
        @NotNull
        Long projectId
) {
}
