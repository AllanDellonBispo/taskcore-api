package com.taskcore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.taskcore.domain.exception.NoUsersFoundException;
import com.taskcore.domain.model.User;
import com.taskcore.domain.repository.UserRepository;

@Service
public class UserRegistrationService {

	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> getAllUsers(){
		try {
			Iterable<User> users = userRepository.findAll();
			
			if(!users.iterator().hasNext()) {
				throw new NoUsersFoundException("Nenhum usuário encontrado.");
			}
			
			return users;
			
		} catch (DataAccessException e) {
			throw new RuntimeException("Erro ao acessar o banco de dados.", e);
		}
	}
	
}
