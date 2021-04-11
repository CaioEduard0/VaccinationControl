package com.example.VaccinationControl.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.VaccinationControl.entities.Vaccine;
import com.example.VaccinationControl.services.VaccineService;

@RestController
@RequestMapping("users")
public class VaccineController {
	
	@Autowired
	private VaccineService vaccineService;
	
	@PostMapping("/{id}/vaccines")
	public ResponseEntity<Vaccine> insertVaccine(@PathVariable Long id, @Valid @RequestBody Vaccine vaccineData) {
		Vaccine vaccine = vaccineService.insertVaccine(id ,vaccineData);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+vaccine.getId()).build().toUri();
		return ResponseEntity.created(uri).body(vaccine); 		
	}
}



