package com.taskcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskcore.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
