package com.emcode.taskmanager.controller;

import com.emcode.taskmanager.dto.TaskCreateRequest;
import com.emcode.taskmanager.dto.TaskPutRequest;
import com.emcode.taskmanager.dto.TaskResponse;
import com.emcode.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createResponse(@Valid @RequestBody TaskCreateRequest request) {
        TaskResponse response = taskService.createResponse(request);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/tasks/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getSingleTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> replaceTask(@PathVariable Long id, @Valid @RequestBody TaskPutRequest request) {
        return ResponseEntity.ok(taskService.replaceTask(id, request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }
}
