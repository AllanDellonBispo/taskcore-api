package com.taskcore.domain.exception;

public class NoProjectsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoProjectsFoundException(String message) {
		super(message);
	}
	
}
