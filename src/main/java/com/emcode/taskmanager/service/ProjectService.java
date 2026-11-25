package com.emcode.taskmanager.service;

import com.emcode.taskmanager.dto.ProjectCreateRequest;
import com.emcode.taskmanager.dto.ProjectDetailedResponse;
import com.emcode.taskmanager.dto.ProjectPutRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.mapper.ProjectMapper;
import com.emcode.taskmanager.model.Project;
import com.emcode.taskmanager.model.User;
import com.emcode.taskmanager.repository.ProjectRepository;
import com.emcode.taskmanager.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

    public ProjectResponse createProject(ProjectCreateRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(()->
                new RecordNotFoundException("User not found: " + request.userId()));
        Project savedProject = projectRepository.save(projectMapper.toEntity(request, new Project(), user));
        return projectMapper.toResponse(savedProject);
    }

    public ProjectResponse findById(Long id) {
        Project project =  getProject(id);
        return projectMapper.toResponse(project);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper:: toResponse)
                .toList();
    }

    public ProjectDetailedResponse findTasksByProjectId(Long id) {
        Project project = getProject(id);
        return projectMapper.toDetailedResponse(project);
    }

    public ProjectResponse replaceProject(Long id, @Valid ProjectPutRequest request) {
        Project projectToUpdate = getProject(id);
        Project updatedProject =  projectMapper.update(projectToUpdate, request);
        return projectMapper.toResponse(projectRepository.save(updatedProject));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Project not found: " + id));
    }
}
