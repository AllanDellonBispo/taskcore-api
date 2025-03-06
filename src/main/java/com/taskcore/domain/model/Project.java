package com.taskcore.domain.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome é obrigatório")
	@Column
	private String name;
	
	@NotNull(message = "A descrição é obrigatória")
	@Column
	private String description;
	
	@Column
	private Priority priority;
	
	
	@NotNull(message = "A data de conclusão")
	@Column
	private LocalDate completionForecast;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Task> tasks;
	
}
