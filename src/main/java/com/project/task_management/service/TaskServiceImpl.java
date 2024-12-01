package com.project.task_management.service;

import com.project.task_management.dto.TaskDTO;
import com.project.task_management.dto.UpdateTaskDTO;
import com.project.task_management.entity.Task;
import com.project.task_management.mapper.TaskMapper;
import com.project.task_management.repository.TaskRepository;
import com.project.task_management.service.impl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.task_management.exception.GlobalExceptionHandler.ConflictException;
import com.project.task_management.exception.GlobalExceptionHandler.NotFoundException;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper,TaskRepository taskRepository){
        this.taskMapper=taskMapper;
        this.taskRepository=taskRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO){

    if (taskRepository.existsByName(taskDTO.getName())) {
        throw new ConflictException("Task with name '" + taskDTO.getName() + "' already exists.");
    }
    Task newTask = taskMapper.toEntity(taskDTO);

        Task savedTask = taskRepository.save(newTask);

    return taskMapper.toApi(savedTask);
}

    @Override
    public TaskDTO updateTask(UpdateTaskDTO taskDTO, long id) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with ID " + id + " not found."));


        if (taskDTO.getName() != null) {
            if (!existingTask.getName().equals(taskDTO.getName()) &&
                    taskRepository.existsByName(taskDTO.getName())) {
                throw new ConflictException("Task with name '" + taskDTO.getName() + "' already exists.");
            }
            existingTask.setName(taskDTO.getName());
        }

        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getStatus() != null) {
            existingTask.setStatus(taskDTO.getStatus());
        }


        taskRepository.save(existingTask);

        return taskMapper.toApi(existingTask);
    }

    @Override
    public void deleteTask(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with ID " + id + " not found."));


        taskRepository.delete(task);
    }

    @Override
    public Page<TaskDTO> getTasks(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage;
        if (name != null && !name.isEmpty()) {
            taskPage = taskRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            taskPage = taskRepository.findAll(pageable);
        }


        if (taskPage.isEmpty()) {
            throw new NotFoundException("No data available for this page.");
        }

        return taskPage.map(taskMapper::toApi);
    }

}

