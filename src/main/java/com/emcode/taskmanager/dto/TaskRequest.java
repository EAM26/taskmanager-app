package com.emcode.taskmanager.dto;

public record TaskRequest(
        String title,
        String description,
        Long projectId

) {
}
