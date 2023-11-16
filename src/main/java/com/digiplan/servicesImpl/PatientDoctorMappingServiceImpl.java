package com.digiplan.servicesImpl;

import com.digiplan.entities.PatientDoctorMapping;
import com.digiplan.repositories.PatientDoctorMappingRepository;
import com.digiplan.services.PatientDoctorMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class PatientDoctorMappingServiceImpl implements PatientDoctorMappingService {
    @Autowired
    private PatientDoctorMappingRepository repository;

    @Override
    public List<PatientDoctorMapping> getAllMappings() {
        return repository.findAll();
    }

    @Override
    public PatientDoctorMapping getMappingById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public PatientDoctorMapping updateMapping(String id, PatientDoctorMapping updatedMapping) {
        PatientDoctorMapping existingMapping = repository.findById(id).get();
        if (existingMapping != null) {
            // Update the fields you want to change
            existingMapping.setPatientName(updatedMapping.getPatientName());
            existingMapping.setMobile(updatedMapping.getMobile());
            existingMapping.setEmail(updatedMapping.getEmail());
            // Save the updated mapping
            return repository.save(existingMapping);
        }
        return null;
    }

    @Override
    public void deleteMappingById(String id) {
        repository.deleteById(id);
    }

    @Override
    public PatientDoctorMapping createMapping(PatientDoctorMapping newMapping) {
        return repository.save(newMapping);
    }

    @Override
    public List<PatientDoctorMapping> getMappingsByMobileOrEmail(String mobileOrEmail) {

        return repository.findByMobileOrEmail(mobileOrEmail, mobileOrEmail);

    }
}

