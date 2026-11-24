package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.ProjectRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project toEntity(ProjectRequest request, Project project, User user) {
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setUser(user);
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getStatus(),
                project.getUser().getId()
        );
    }
}
