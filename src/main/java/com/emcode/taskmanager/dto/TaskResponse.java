package com.emcode.taskmanager.dto;

import com.emcode.taskmanager.model.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        boolean isArchived,
        Long projectId

) {
}
