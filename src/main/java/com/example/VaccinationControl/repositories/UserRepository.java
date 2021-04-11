package com.example.VaccinationControl.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VaccinationControl.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByCpf(String cpf);
}

