package com.example.VaccinationControl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VaccinationControl.entities.User;
import com.example.VaccinationControl.exceptions.RepeatedCPF;
import com.example.VaccinationControl.exceptions.RepeatedEmail;
import com.example.VaccinationControl.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User insertUser(User userData) {
		Optional<User> user1 = userRepository.findByEmail(userData.getEmail());
		if (user1.isEmpty()) {
			Optional<User> user2 = userRepository.findByCpf(userData.getCpf());
			if (user2.isEmpty()) {
				return userRepository.save(userData);
			} else {
				throw new RepeatedCPF(userData.getCpf());
			}
		} else {
			throw new RepeatedEmail(userData.getEmail());
		}
	}
}
