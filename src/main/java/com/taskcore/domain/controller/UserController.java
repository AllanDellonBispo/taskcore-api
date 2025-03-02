package com.taskcore.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskcore.domain.exception.NoUsersFoundException;
import com.taskcore.domain.model.User;
import com.taskcore.domain.service.UserRegistrationService;

@RestController
@RequestMapping("/users")
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

}
