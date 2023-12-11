package com.digiplan.servicesImpl;

import com.digiplan.entities.PatientDoctorMapping;
import com.digiplan.repositories.PatientDoctorMappingRepository;
import com.digiplan.services.PatientDoctorMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDoctorMappingServiceImpl implements PatientDoctorMappingService {
    @Autowired
    private PatientDoctorMappingRepository repository;

    @Override
    public List<PatientDoctorMapping> getAllMappings() {
        return repository.findAll();
    }

    @Override
    public PatientDoctorMapping getMappingById(Long id) {
        Optional<PatientDoctorMapping> optionalMapping = repository.findById(id);
        return optionalMapping.orElse(null);
    }

    @Override
    public PatientDoctorMapping getMappingByCaseId(String caseId) {
        return repository.findByCaseId(caseId).orElse(null);
    }

    @Override
    public PatientDoctorMapping updateMapping(String id, PatientDoctorMapping updatedMapping) {
        PatientDoctorMapping existingMapping = repository.findById(id).get();
        if (existingMapping != null) {
            existingMapping.setPatientName(updatedMapping.getPatientName());
            existingMapping.setMobile(updatedMapping.getMobile());
            existingMapping.setEmail(updatedMapping.getEmail());
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

