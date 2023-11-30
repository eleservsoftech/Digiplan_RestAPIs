package com.digiplan.repositories;

import com.digiplan.entities.ScanBookingPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanBookingPatientRepository  extends JpaRepository<ScanBookingPatient, String> {
}
