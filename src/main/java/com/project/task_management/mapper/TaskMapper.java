package com.project.task_management.mapper;

import com.project.task_management.dto.TaskDTO;
import com.project.task_management.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskDTO taskdto){

           Task newTask = new Task();

           newTask.setName(taskdto.getName());
           newTask.setDescription(taskdto.getDescription());
           newTask.setStatus(taskdto.getStatus());

           return newTask;

    }

    public TaskDTO toApi(Task task){

        TaskDTO newtaskdto = new TaskDTO();

        newtaskdto.setId(task.getId());
        newtaskdto.setName(task.getName());
        newtaskdto.setDescription(task.getDescription());
        newtaskdto.setStatus(task.getStatus());
        newtaskdto.setCreatedAt(task.getCreation());
        newtaskdto.setLastModifiedAt(task.getModification());

        return newtaskdto;

    }
}
