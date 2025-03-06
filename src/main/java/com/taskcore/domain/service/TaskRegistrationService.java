package com.taskcore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.taskcore.domain.exception.NoTasksFoundException;
import com.taskcore.domain.model.Priority;
import com.taskcore.domain.model.Task;
import com.taskcore.domain.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskRegistrationService {

	@Autowired
	TaskRepository taskRepository;
	
	
	public Iterable<Task> getAllTask(){
		try {
			Iterable<Task> tasks = taskRepository.findAll();
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Task getTaskForId(Long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new NoTasksFoundException("Tarefa não encontrada."));
		
		try {
			return task;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}

	public Iterable<Task> getTaskForFinishedTrue(){
		try {
			Iterable<Task> tasks = taskRepository.findByFinishedIsTrue();
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Iterable<Task> getTaskForFinishedFalse(){
		try {
			Iterable<Task> tasks = taskRepository.findByFinishedIsFalse();
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Iterable<Task> getTaskForPriorityLow(){
		try {
			Iterable<Task> tasks = taskRepository.findByPriority(Priority.LOW);
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}

	public Iterable<Task> getTaskForPriorityMedium(){
		try {
			Iterable<Task> tasks = taskRepository.findByPriority(Priority.MEDIUM);
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}

	public Iterable<Task> getTaskForPriorityHigh(){
		try {
			Iterable<Task> tasks = taskRepository.findByPriority(Priority.HIGH);
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Iterable<Task> getTaskForCompletionForecasthRecent(){
		try {
			Iterable<Task> tasks = taskRepository.findAllByOrderByCompletionForecastAsc();
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}

	public Iterable<Task> getTaskForCompletionForecasthOld(){
		try {
			Iterable<Task> tasks = taskRepository.findAllByOrderByCompletionForecastDesc();
			
			if(!tasks.iterator().hasNext()) {
				throw new NoTasksFoundException("Nenhuma tarefa encontrada.");
			}
			
			return tasks;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Task save(Task task) {
		try {
			return taskRepository.save(task);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar tarefa.", e);
		}
	}
	
	@Transactional
	public Task update(Long id, Task taskUpdate) {
		
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new NoTasksFoundException("Tarefa não encontrada."));
		
		task.setTitle(taskUpdate.getTitle());
		task.setDescription(taskUpdate.getDescription());
		task.setFinished(taskUpdate.isFinished());
		task.setPriority(taskUpdate.getPriority());
		task.setCompletionForecast(taskUpdate.getCompletionForecast());
		
		try {
			return taskRepository.save(task);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar tarefa.", e);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new NoTasksFoundException("Tarefa não encontrada."));
		
		try {
			taskRepository.deleteById(task.getId());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar tarefa.", e);
		}
	}
	
}
