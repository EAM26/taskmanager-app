package com.emcode.taskmanager.service;

import com.emcode.taskmanager.dto.ProjectDetailedResponse;
import com.emcode.taskmanager.dto.ProjectRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.mapper.ProjectMapper;
import com.emcode.taskmanager.mapper.TaskMapper;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.User;
import com.emcode.taskmanager.repository.ProjectRepository;
import com.emcode.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public ProjectResponse createProject(ProjectRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(()->
                new RecordNotFoundException("User not found: " + request.userId()));
        Project savedProject = projectRepository.save(projectMapper.toEntity(request, new Project(), user));
        return projectMapper.toResponse(savedProject);
    }

    public ProjectResponse findById(Long id) {
        Project project =  projectRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Project not found: " + id));
        return projectMapper.toResponse(project);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper:: toResponse)
                .toList();
    }

    public ProjectDetailedResponse findTasksByProjectId(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Project not found: " + id));
        return projectMapper.toDetailedResponse(project);
    }
}
