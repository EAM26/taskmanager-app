package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.ProjectDetailedResponse;
import com.emcode.taskmanager.dto.ProjectCreateRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.dto.TaskResponse;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.User;
import com.emcode.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final TaskRepository taskRepository;
    private final TaskMapper taskmapper;

    public Project toEntity(ProjectCreateRequest request, Project project, User user) {
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

    public ProjectDetailedResponse toDetailedResponse(Project project) {
        List<TaskResponse> tasks = taskRepository.findAllByProjectId(project.getId())
                .stream()
                .map(taskmapper:: toResponse)
                .toList();

        return new ProjectDetailedResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getStatus(),
                project.getUser().getId(),
                tasks
        );
    }
}
