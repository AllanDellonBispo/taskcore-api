package com.taskcore.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.taskcore.domain.exception.NoProjectsFoundException;
import com.taskcore.domain.model.Project;
import com.taskcore.domain.service.ProjectRegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {

	@Autowired
	ProjectRegistrationService projectRegistrationService;
	
//	Corrigir mensagem quando não tem projects não aparece
	@GetMapping
	public ResponseEntity<?> getAll(){
		try {
			Iterable<Project> projects = projectRegistrationService.getAllProjects();
			return ResponseEntity.status(HttpStatus.OK).body(projects);
		} catch (NoProjectsFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectId(@PathVariable Long id){
		try {
			Project project = projectRegistrationService.getProjectById(id);
			return ResponseEntity.status(HttpStatus.OK).body(project);
		} catch (NoProjectsFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Project project){
		try {
			Project savedProject = projectRegistrationService.saveProject(project);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Project projectUpdate){
		try {
			Project project = projectRegistrationService.updateProject(id, projectUpdate);
			return ResponseEntity.status(HttpStatus.OK).body(project);
		} catch (NoProjectsFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			projectRegistrationService.deleteProject(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NoProjectsFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
}
