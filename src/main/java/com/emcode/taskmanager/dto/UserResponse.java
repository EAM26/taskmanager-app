package com.emcode.taskmanager.dto;

import java.util.List;

public record UserResponse(
        Long id,
        String username,
        String email,
        List<ProjectResponse> projectResponses
) {
}
