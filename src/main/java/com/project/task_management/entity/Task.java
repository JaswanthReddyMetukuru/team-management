package com.project.task_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private long id;

    @Column(name="team_name",nullable=false,unique=true)
    @NotBlank
    private String name;

    @Column(name="team_description")
    private String description;

    @Column(name="team_status")
    private String status;

    @Column(name="team_created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime creation;

    @Column(name="team_modified_at")
    @UpdateTimestamp
    private LocalDateTime modification;
}

