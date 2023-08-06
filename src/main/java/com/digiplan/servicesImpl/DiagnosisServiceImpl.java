package com.digiplan.servicesImpl;

import com.digiplan.entities.Diagnosis;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.DiagnosisRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Diagnosis getDiagnosisData(String id) {
        Diagnosis diagnosis = null;
        try {
            Optional<Diagnosis> check = diagnosisRepository.findById(id.replace('$', '/'));
            if (check.isPresent())
                diagnosis = diagnosisRepository.getById(id.replace('$', '/'));
        } catch (Exception exception) {
            System.out.println("@getDiagnosisData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDiagnosisData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> getAllDiagnosisData() {
        List<Diagnosis> diagnosisList = null;
        try {
            diagnosisList = diagnosisRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllDiagnosisData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllDiagnosisData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return diagnosisList;
    }

    @Override
    public Diagnosis addDiagnosisData(Diagnosis diagnosisData) {
        Diagnosis diagnosis = null;
        try {
            diagnosisData
                    .setId(LocalDateTime.now() + "_" + UUID.randomUUID() + "_" + diagnosisData.getIncompleteFormId());
            diagnosis = diagnosisRepository.saveAndFlush(diagnosisData);
        } catch (Exception exception) {
            System.out.println("@addDiagnosisData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addDiagnosisData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return diagnosis;
    }

    @Override
    public Diagnosis updateDiagnosisData(String id, Diagnosis diagnosisData) {
        Diagnosis diagnosis = null;
        try {
            Optional<Diagnosis> check = diagnosisRepository.findById(id.replace('$', '/'));
            if (check.isPresent())
                diagnosis = diagnosisRepository.saveAndFlush(diagnosisData);
        } catch (Exception exception) {
            System.out.println("@updateDiagnosisData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateDiagnosisData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return diagnosis;
    }

    @Override
    public String deleteDiagnosisData(String id) {
        String status = "";
        try {
            diagnosisRepository.deleteById(id.replace('$', '/'));
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteDiagnosisData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteDiagnosisData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
