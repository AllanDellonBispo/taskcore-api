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

import com.taskcore.domain.exception.NoUsersFoundException;
import com.taskcore.domain.exception.UserAlreadyExistsException;
import com.taskcore.domain.model.User;
import com.taskcore.domain.service.UserRegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	
	@Autowired
	UserRegistrationService userRegistartionService;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getAllUsers(){
		try {
			Iterable<User> users = userRegistartionService.getAllUsers();
			
			return ResponseEntity.ok(users);
			
		} catch (NoUsersFoundException  e) {
			return ResponseEntity.noContent().build();
		}catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PostMapping
	@Operation(summary = "Cadastro de Usuário")
	public ResponseEntity<?> save(@Valid @RequestBody User user){
		try {
			User savedUser = userRegistartionService.save(user);
			return ResponseEntity.status(201).body(savedUser);
			
		} catch (UserAlreadyExistsException  e) {
			return ResponseEntity.status(409).body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user){
		try {
			User userUpdate = userRegistartionService.updateUser(id, user);
			return ResponseEntity.ok(userUpdate);
		} catch (NoUsersFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			userRegistartionService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

}
