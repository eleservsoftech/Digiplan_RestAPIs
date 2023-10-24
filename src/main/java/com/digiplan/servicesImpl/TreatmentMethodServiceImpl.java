package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.TreatmentMethod;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.TreatmentMethodRepository;
import com.digiplan.services.TreatmentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreatmentMethodServiceImpl implements TreatmentMethodService {

    @Autowired
    private TreatmentMethodRepository treatmentMethodRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public TreatmentMethod getTreatmentMethodData(String id) {
        TreatmentMethod treatmentMethod = null;
        try {
            Optional<TreatmentMethod> check = treatmentMethodRepository.findById(id.replace('$', '/'));
            if (check.isPresent())
                treatmentMethod = treatmentMethodRepository.getById(id.replace('$', '/'));
        } catch (Exception exception) {
            System.out.println("@getTreatmentMethodData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getTreatmentMethodData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return treatmentMethod;
    }

    @Override
    public List<TreatmentMethod> getAllTreatmentMethodData() {
        List<TreatmentMethod> treatmentMethodList = null;
        try {
            treatmentMethodList = treatmentMethodRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllTreatmentMethodData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllTreatmentMethodData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return treatmentMethodList;
    }

    @Override
    public TreatmentMethod addTreatmentMethodData(TreatmentMethod treatmentMethodData) {
        TreatmentMethod treatmentMethod = null;
        try {
            treatmentMethodData.setId(
                    LocalDateTime.now() + "_" + UUID.randomUUID() + "_" + treatmentMethodData.getIncompleteFormId());
            treatmentMethod = treatmentMethodRepository.saveAndFlush(treatmentMethodData);
        } catch (Exception exception) {
            System.out.println("@addTreatmentMethodData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addTreatmentMethodData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return treatmentMethod;
    }

    @Override
    public TreatmentMethod updateTreatmentMethodData(String id, TreatmentMethod treatmentMethodData) {
        TreatmentMethod treatmentMethod = null;
        try {
            Optional<TreatmentMethod> check = treatmentMethodRepository.findById(id.replace('$', '/'));
            if (check.isPresent())
                treatmentMethod = treatmentMethodRepository.saveAndFlush(treatmentMethodData);
        } catch (Exception exception) {
            System.out.println("@updateTreatmentMethodData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateTreatmentMethodData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return treatmentMethod;
    }

    @Override
    public String deleteTreatmentMethodData(String id) {
        String status = "";
        try {
            treatmentMethodRepository.deleteById(id.replace('$', '/'));
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteTreatmentMethodData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteTreatmentMethodData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
