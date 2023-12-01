package com.digiplan.services;

import com.digiplan.entities.PatientDoctorMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PatientDoctorMappingService {
    List<PatientDoctorMapping> getAllMappings();

    PatientDoctorMapping getMappingById(Long id);

    PatientDoctorMapping getMappingByCaseId(String caseId);

    PatientDoctorMapping updateMapping(String id, PatientDoctorMapping updatedMapping);

    void deleteMappingById(String id);

    PatientDoctorMapping createMapping(PatientDoctorMapping newMapping);

    List<PatientDoctorMapping> getMappingsByMobileOrEmail(String mobileOrEmail);


}
