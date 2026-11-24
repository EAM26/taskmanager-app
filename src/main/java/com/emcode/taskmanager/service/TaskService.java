package com.emcode.taskmanager.service;

import com.emcode.taskmanager.dto.ProjectDetailedResponse;
import com.emcode.taskmanager.dto.TaskRequest;
import com.emcode.taskmanager.dto.TaskResponse;
import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.mapper.TaskMapper;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.Task;
import com.emcode.taskmanager.repository.ProjectRepository;
import com.emcode.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;

    public TaskResponse createResponse(TaskRequest request) {
        Project project = projectRepository.findById(request.projectId()).orElseThrow(() ->
                new RecordNotFoundException("Project not found: " + request.projectId()));
        Task savedTask = taskRepository.save(taskMapper.toEntity(request, new Task(), project));
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Task not found" + id));
        return taskMapper.toResponse(task);
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }


}
