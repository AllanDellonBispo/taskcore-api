package com.taskcore.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskcore.domain.exception.NoTasksFoundException;
import com.taskcore.domain.model.Task;
import com.taskcore.domain.service.TaskRegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

	@Autowired
	TaskRegistrationService taskRegistrationService; 
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getAllTask();
			
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskId(@PathVariable Long id){
		try {
			Task task = taskRegistrationService.getTaskForId(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(task);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/finished/true")
	public ResponseEntity<?> getTaskFinishedTrue(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForFinishedTrue();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/finished/false")
	public ResponseEntity<?> getTaskFinishedFalse(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForFinishedFalse();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/priority/low")
	public ResponseEntity<?> getTaskPriorityLow(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForPriorityLow();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/priority/medium")
	public ResponseEntity<?> getTaskPriorityMedium(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForPriorityMedium();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/priority/high")
	public ResponseEntity<?> getTaskPriorityHigh(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForPriorityHigh();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/recent")
	public ResponseEntity<?> getTaskCompletionForecasthRecent(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForCompletionForecasthRecent();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
			} catch (NoTasksFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (DataAccessException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
	}
	
	@GetMapping("/old")
	public ResponseEntity<?> getTaskCompletionForecasthOld(){
		try {
			Iterable<Task> tasks = taskRegistrationService.getTaskForCompletionForecasthOld();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
			} catch (NoTasksFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (DataAccessException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
	}
	
	@PostMapping
	public ResponseEntity<?> saveTask(@Valid @RequestBody Task task){
		try {
			Task savedUser = taskRegistrationService.save(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
		try {
			Task savedUser = taskRegistrationService.update(id, task);
			return ResponseEntity.status(HttpStatus.OK).body(savedUser);
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id){
		try {
			taskRegistrationService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NoTasksFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
