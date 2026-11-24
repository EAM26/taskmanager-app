package com.emcode.taskmanager.dto;

import com.emcode.taskmanager.model.ProjectStatus;

import java.util.List;

public record ProjectDetailedResponse(
        Long Id,
        String title,
        String description,
        ProjectStatus status,
        Long userId,
        List<TaskResponse> tasks
) {
}
