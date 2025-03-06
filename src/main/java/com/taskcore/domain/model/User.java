package com.taskcore.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	@Column(unique = true)
	private String name;
	
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
	@Column(unique = true)
	private String email;
	
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	@Column
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Project> projects;
	
}
