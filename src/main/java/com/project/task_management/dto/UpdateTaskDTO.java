package com.project.task_management.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTaskDTO {
    @Size(max = 30, message = "Name must be less than 255 characters.")
    private String name;

    @Size(max = 250, message = "Description must be less than 500 characters.")
    private String description;

    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED", message = "Invalid status values allowed= PENDING|IN_PROGRESS|COMPLETED")
    private String status;


}
