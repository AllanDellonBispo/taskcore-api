package com.taskcore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.taskcore.domain.exception.NoProjectsFoundException;
import com.taskcore.domain.model.Project;
import com.taskcore.domain.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectRegistrationService {

	@Autowired
	ProjectRepository projectRepository;
	
	public Iterable<Project> getAllProjects(){		
		try {
			Iterable<Project> projects = projectRepository.findAll();
			
			if(!projects.iterator().hasNext()) {
				throw new NoProjectsFoundException("Nenhum projeto encontrada.");
			}
			return projects;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
		
	public Project getProjectById(Long id) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new NoProjectsFoundException("Nenhum projeto encontrado."));
		
		try {
			return project;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
	public Project saveProject(Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao cadastrar projeto", e);
		}
	}
	
	@Transactional
	public Project updateProject(Long id, Project projectUpdate) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new NoProjectsFoundException("Nenhum projeto encontrado."));
		
		project.setName(projectUpdate.getName());
		project.setDescription(projectUpdate.getDescription());
		project.setPriority(projectUpdate.getPriority());
		project.setCompletionForecast(projectUpdate.getCompletionForecast());
		
		try {
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar projeto", e);
		}
	}
	
	@Transactional
	public void deleteProject(Long id) {
		projectRepository.findById(id)
				.orElseThrow(() -> new NoProjectsFoundException("Nenhum projeto encontrado."));
		
		try {
			projectRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar projeto", e);
		}
	}

	
}
