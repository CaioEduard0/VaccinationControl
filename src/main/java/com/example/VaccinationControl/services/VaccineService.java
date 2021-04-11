package com.example.VaccinationControl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VaccinationControl.entities.User;
import com.example.VaccinationControl.entities.Vaccine;
import com.example.VaccinationControl.exceptions.UserNotFound;
import com.example.VaccinationControl.repositories.UserRepository;
import com.example.VaccinationControl.repositories.VaccineRepository;

@Service
public class VaccineService {
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Vaccine insertVaccine(Long id, Vaccine vaccine) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			vaccine.setUser(user.get());
			return vaccineRepository.save(vaccine);
		} else {
			throw new UserNotFound(id);
		}		
	}
}
