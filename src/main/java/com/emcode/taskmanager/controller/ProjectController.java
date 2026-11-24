package com.emcode.taskmanager.controller;

import com.emcode.taskmanager.dto.ProjectRequest;
import com.emcode.taskmanager.dto.ProjectResponse;
import com.emcode.taskmanager.service.ProjectService;
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

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }
}
