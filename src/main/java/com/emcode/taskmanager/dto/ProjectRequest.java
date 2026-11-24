package com.emcode.taskmanager.dto;

public record ProjectRequest(
        String title,
        String description,
        Long userId
) {
}
