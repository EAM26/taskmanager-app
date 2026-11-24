package com.emcode.taskmanager.controller;

import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.model.User;
import com.emcode.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;

    @GetMapping("/{id}")
    public User getSingleUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found: " + id));
    }
}
