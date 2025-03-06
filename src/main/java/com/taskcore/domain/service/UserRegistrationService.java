package com.taskcore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.taskcore.domain.exception.NoUsersFoundException;
import com.taskcore.domain.exception.UserAlreadyExistsException;
import com.taskcore.domain.model.User;
import com.taskcore.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

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
	
	public User getUser(Long id){
		return userRepository.findById(id)
				.orElseThrow(() -> new NoUsersFoundException("Nenhum usuário encontrado com esse id"));
	}
	
	public User save(User user) {

		if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException("Já existe um usuário com este email");
		}
				
			return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(Long id, User userUpdate) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NoUsersFoundException("Usuário não encontrado."));
		
		user.setName(userUpdate.getName());
		user.setEmail(userUpdate.getEmail());
		user.setPassword(userUpdate.getPassword());
		
		try {
			return userRepository.save(user);			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar usuário.");
		}
		
	}
	
	@Transactional
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NoUsersFoundException("Id de usuário inválido"));
		
		try {
			userRepository.deleteById(user.getId());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar usuário.");
		}
	}
	
}
