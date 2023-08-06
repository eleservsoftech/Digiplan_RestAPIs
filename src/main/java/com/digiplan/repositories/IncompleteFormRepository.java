package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.IncompleteForm;

public interface IncompleteFormRepository extends JpaRepository<IncompleteForm, Integer> {

}
