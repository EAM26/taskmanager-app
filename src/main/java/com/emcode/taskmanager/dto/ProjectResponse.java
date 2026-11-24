package com.emcode.taskmanager.dto;

import com.emcode.taskmanager.model.ProjectStatus;

public record ProjectResponse(
        Long Id,
        String title,
        String description,
        ProjectStatus status,
        Long userId
) {
}
