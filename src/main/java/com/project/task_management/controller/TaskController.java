package com.project.task_management.controller;

import com.project.task_management.dto.TaskDTO;
import com.project.task_management.dto.UpdateTaskDTO;
import com.project.task_management.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl){
        this.taskServiceImpl=taskServiceImpl;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskdto){
        TaskDTO newtaskdto=taskServiceImpl.createTask(taskdto);
        return new ResponseEntity<>(newtaskdto, HttpStatus.CREATED);
    }


    @PutMapping("/{task_id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid UpdateTaskDTO taskdto, @PathVariable("task_id") long id){
        TaskDTO updatedtaskdto=taskServiceImpl.updateTask(taskdto,id);
        return ResponseEntity.ok(updatedtaskdto);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable("task_id") long id){
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.ok("{  Task with ID: " + id + " has been deleted successfully");
    }

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getTasksByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {


        Page<TaskDTO> tasks = taskServiceImpl.getTasks(page, size, name);
        return ResponseEntity.ok(tasks);
    }



}
