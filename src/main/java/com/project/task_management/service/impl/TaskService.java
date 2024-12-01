package com.project.task_management.service.impl;

import com.project.task_management.dto.TaskDTO;
import com.project.task_management.dto.UpdateTaskDTO;
import org.springframework.data.domain.Page;

public interface TaskService {

    public TaskDTO createTask(TaskDTO taskDTO);
    public TaskDTO updateTask( UpdateTaskDTO taskDTO, long id);
    public void deleteTask(long id);
    public Page<TaskDTO> getTasks(int page, int size, String name);
}
