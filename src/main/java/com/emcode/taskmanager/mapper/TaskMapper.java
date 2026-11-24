package com.emcode.taskmanager.mapper;

import com.emcode.taskmanager.dto.TaskRequest;
import com.emcode.taskmanager.dto.TaskResponse;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequest request, Task task, Project project) {
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
}
