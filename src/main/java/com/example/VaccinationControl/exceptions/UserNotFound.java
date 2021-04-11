package com.example.VaccinationControl.exceptions;

public class UserNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserNotFound(Long id) {
		super("It was not possible to insert the vaccine, because the user with id "+ id +" does not exist.");
	}
}

