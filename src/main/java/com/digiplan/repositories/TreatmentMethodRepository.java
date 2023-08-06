package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.TreatmentMethod;

public interface TreatmentMethodRepository extends JpaRepository<TreatmentMethod, String> {

}
