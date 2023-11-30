package com.digiplan.repositories;

import com.digiplan.entities.PatientDoctorMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDoctorMappingRepository extends JpaRepository<PatientDoctorMapping, String> {
    Optional<PatientDoctorMapping> findById(String id);

    List<PatientDoctorMapping> findByMobileOrEmail(String mobile, String email);


}
