package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, String> {

}
