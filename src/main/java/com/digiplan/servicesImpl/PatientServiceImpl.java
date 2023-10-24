package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.Patient;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.PatientRepository;
import com.digiplan.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public ResponseEntity<Map> addPatient(Patient addPatient) {
    Map<Object, Object> map = new HashMap<>();
    HttpStatus status = null;
    try {

        if(!addPatient.getCaseId().isEmpty())
        {
            Patient  PatientObj = patientRepository.save(addPatient);
            map.put("status_code", "201");
            map.put("message", "Patient data saved successfully");
            map.put("data", PatientObj);
            status = HttpStatus.CREATED;
        }else {
            map.put("status_code", "400");
            map.put("message", "Case id cannot be blank!");
            map.put("data", "Patient data not saved");
            status = HttpStatus.BAD_REQUEST;
        }
    } catch (Exception ex) {
        map.put("status_code", "500");
        map.put("message", ex.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity(map, status);
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
