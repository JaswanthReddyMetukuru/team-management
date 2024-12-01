package com.project.task_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    private long id;


    @NotEmpty
    @Size(min=1 , max=30, message="check the length")
    private String name;
    @Size(min=1 , max=250, message="check the length")
    private String description;
    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED", message = "Invalid status values allowed= PENDING|IN_PROGRESS|COMPLETED")
    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
