package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
