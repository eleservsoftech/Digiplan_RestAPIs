package com.digiplan.repositories;

import com.digiplan.entities.PatientHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientHistoryRepository extends JpaRepository<PatientHistoryEntity,Long> {


}
