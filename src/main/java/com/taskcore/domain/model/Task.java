package com.taskcore.domain.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O título é obrigatório")
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private boolean finished;
	
	@NotNull(message = "A prioridade é obrigatória")
	@Enumerated(EnumType.STRING)
	@Column
	private Priority priority;
	
	@NotNull(message = "A data de finalização é obrigatória")
	@Column
	private LocalDate completionForecast;
	
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	@JsonBackReference
	private Project project;
	
}
