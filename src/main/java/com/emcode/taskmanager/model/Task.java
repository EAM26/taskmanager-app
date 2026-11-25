package com.emcode.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;
    private boolean isArchived = false;
    @ManyToOne
    private Project project;
}
