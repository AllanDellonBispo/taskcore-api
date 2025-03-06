package com.taskcore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskcore.domain.model.Priority;
import com.taskcore.domain.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByFinishedIsTrue();
	
	List<Task> findByFinishedIsFalse();
	
	List<Task> findByPriority(Priority priority);
	
	List<Task> findAllByOrderByCompletionForecastAsc();
	
	List<Task> findAllByOrderByCompletionForecastDesc();
	

}
