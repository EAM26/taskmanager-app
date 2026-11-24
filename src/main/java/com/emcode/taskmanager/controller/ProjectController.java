package com.emcode.taskmanager.controller;

import com.emcode.taskmanager.dto.ProjectDetailedResponse;
import com.emcode.taskmanager.dto.ProjectRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.service.ProjectService;
import com.emcode.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) {
        ProjectResponse response = projectService.createProject(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/projects/{id}")
                .buildAndExpand(response.Id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getSingleProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<ProjectDetailedResponse> getTasksByProjectId(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findTasksByProjectId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }
}
