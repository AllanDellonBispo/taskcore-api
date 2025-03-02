package com.taskcore.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testes")
public class TesteController {

	@GetMapping("/foi")
	public String teste() {
		return "Foi";
	}
	
}
