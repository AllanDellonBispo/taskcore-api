package com.taskcore.domain.exception;

public class NoTasksFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoTasksFoundException(String message) {
		super(message);
	}
	
	
}
