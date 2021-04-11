package com.example.VaccinationControl.exceptions;

public class RepeatedCPF extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RepeatedCPF(String cpf) {
		super("The CPF "+ cpf +" is already registered!");
	}
}
