package com.project.task_management.repository;

import com.project.task_management.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {


    boolean existsByName(String name);
    Page<Task> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
