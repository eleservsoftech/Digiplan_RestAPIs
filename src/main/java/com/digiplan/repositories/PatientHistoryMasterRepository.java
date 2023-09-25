package com.digiplan.repositories;

import com.digiplan.entities.PatientHistoryEntity;
import com.digiplan.entities.PatientHistoryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientHistoryMasterRepository extends JpaRepository<PatientHistoryMasterEntity, Integer> {

}
