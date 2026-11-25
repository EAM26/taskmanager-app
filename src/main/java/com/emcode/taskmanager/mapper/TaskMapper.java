package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.TaskCreateRequest;
import com.emcode.taskmanager.dto.TaskPutRequest;
import com.emcode.taskmanager.dto.TaskResponse;
import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.Task;
import com.emcode.taskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final ProjectRepository projectRepository;

    public Task toEntity(TaskCreateRequest request, Task task, Project project) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setProject(project);

        return task;
    }

    public TaskResponse toResponse(Task savedTask) {
        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.isArchived(),
                savedTask.getProject().getId()
        );
    }

    public Task update(Task task, TaskPutRequest request) {
        Project project = projectRepository.findById(request.projectId()).orElseThrow(() ->
                new RecordNotFoundException("Project not found: " + request.projectId()));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setArchived(request.isArchived());
        task.setProject(project);
        return task;
    }
}
