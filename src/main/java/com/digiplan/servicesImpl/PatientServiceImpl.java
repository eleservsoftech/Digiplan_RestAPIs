package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.Patient;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.PatientRepository;
import com.digiplan.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Patient getPatient(String caseId) {
        Patient patient = null;
        try {
            Optional<Patient> check = patientRepository.findById(caseId);
            if (check.isPresent())
                patient = patientRepository.getById(caseId);
        } catch (Exception exception) {
            System.out.println("@getPatient Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getPatient", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patientsList = null;
        try {
            patientsList = patientRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllPatients Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllPatients", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return patientsList;
    }

    @Override
    public Patient addPatient(Patient patientData) {
        Patient patient = null;
        try {
            patient = patientRepository.saveAndFlush(patientData);
        } catch (Exception exception) {
            System.out.println("@addPatient Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addPatient", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return patient;
    }

    @Override
    public Patient updatePatient(String caseId, Patient patientData) {
        Patient patient = null;
        try {
            Optional<Patient> check = patientRepository.findById(caseId);
            if (check.isPresent())
                patient = patientRepository.saveAndFlush(patientData);
        } catch (Exception exception) {
            System.out.println("@updatePatient Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updatePatient", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return patient;
    }

    @Override
    public String deletePatient(String caseId) {
        String status = "";
        try {
            patientRepository.deleteById(caseId);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deletePatient Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deletePatient", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
