package com.taskcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskcore.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
