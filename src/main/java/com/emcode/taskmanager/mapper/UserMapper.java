package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.dto.UserRequest;
import com.emcode.taskmanager.dto.UserResponse;
import com.emcode.taskmanager.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ProjectMapper projectMapper;

    public UserResponse toResponse(User user) {
        List<ProjectResponse> projects = user.getProjects()
                .stream()
                .map(projectMapper:: toResponse)
                .toList();
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                projects
        );
    }

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return user;
    }
}
