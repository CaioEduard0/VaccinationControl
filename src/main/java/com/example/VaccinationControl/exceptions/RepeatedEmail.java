package com.example.VaccinationControl.exceptions;

public class RepeatedEmail extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RepeatedEmail(String email) {
		super("The e-mail "+ email +" is already registered!");
	}
}
