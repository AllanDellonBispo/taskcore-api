package com.taskcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskcore.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
