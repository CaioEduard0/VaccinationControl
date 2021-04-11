package com.example.VaccinationControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VaccinationControl.entities.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {}

