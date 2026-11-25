package com.emcode.taskmanager.service;

import com.emcode.taskmanager.dto.UserCreateRequest;
import com.emcode.taskmanager.dto.UserResponse;
import com.emcode.taskmanager.error.RecordNotFoundException;
import com.emcode.taskmanager.mapper.UserMapper;
import com.emcode.taskmanager.model.User;
import com.emcode.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper usermapper;

    public UserResponse createUser(UserCreateRequest request) {
        User savedUser = userRepository.save(usermapper.toEntity(request));
        return usermapper.toResponse(savedUser);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found: " + id));
        return usermapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(usermapper::toResponse)
                .toList();
    }
}
