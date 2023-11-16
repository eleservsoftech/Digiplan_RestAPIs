package com.digiplan.servicesImpl;

import com.digiplan.entities.ScanBookingPatient;
import com.digiplan.repositories.ScanBookingPatientRepository;
import com.digiplan.services.ScanBookingPatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScanBookingPatientServiceImpl  implements ScanBookingPatientService {
    private final ScanBookingPatientRepository repository;

    public ScanBookingPatientServiceImpl(ScanBookingPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ScanBookingPatient> getAllScanBookingPatients() {
        return repository.findAll();
    }

    @Override
    public ScanBookingPatient getScanBookingPatientById(String scanId) {
        return repository.findById(scanId).orElse(null);
    }

    @Override
    public ScanBookingPatient createScanBookingPatient(ScanBookingPatient scanBookingPatient) {
        return repository.save(scanBookingPatient);
    }

    @Override
    public ScanBookingPatient updateScanBookingPatient(String scanId, ScanBookingPatient updatedScanBookingPatient) {
        ScanBookingPatient existingScanBookingPatient = repository.findById(scanId).orElse(null);
        if (existingScanBookingPatient != null) {
            BeanUtils.copyProperties(updatedScanBookingPatient, existingScanBookingPatient, "scanId");
            return repository.save(existingScanBookingPatient);
        }
        return null;
    }

//    @Override
//    public boolean deleteScanBookingPatient(String scanId) {
//        repository.deleteById(scanId);
//        return false;
//    }

    @Override
    public boolean deleteScanBookingPatient(String scanId) {
        try {
            repository.deleteById(scanId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
