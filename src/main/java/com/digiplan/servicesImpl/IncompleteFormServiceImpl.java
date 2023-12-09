package com.digiplan.servicesImpl;

import com.digiplan.entities.Cases;
import com.digiplan.entities.IncompleteForm;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.IncompleteFormRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.IncompleteFormService;
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
public class IncompleteFormServiceImpl implements IncompleteFormService {

    @Autowired
    private IncompleteFormRepository incompleteFormRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public ResponseEntity<Map> getIncompleteForm(Integer id) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            Optional<IncompleteForm> check = incompleteFormRepository.findById(id);
            if (check.isPresent()) {
                IncompleteForm incompleteForm = incompleteFormRepository.getById(id);
                map.put("status", 200);
                map.put("message", "Record Found!");
                map.put("data", incompleteForm);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Record Found!");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getIncompleteForm Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getIncompleteForm", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getAllIncompleteForms() {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            List<IncompleteForm> incompleteFormsList = incompleteFormRepository.findAll();
            if (incompleteFormsList.isEmpty()) {
                map.put("status", 404);
                map.put("message", "Record Found!");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "Record Found!");
                map.put("data", incompleteFormsList);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@getAllIncompleteForms Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllIncompleteForms", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> addIncompleteForm(IncompleteForm incompleteFormData) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            IncompleteForm incompleteForm = incompleteFormRepository.saveAndFlush(incompleteFormData);
            map.put("status", 200);
            map.put("message", "Data Saved!");
            map.put("data", incompleteForm);
            status = HttpStatus.OK;
        } catch (Exception exception) {
            System.out.println("@addIncompleteForm Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addIncompleteForm", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public IncompleteForm updateIncompleteForm(Integer id, IncompleteForm incompleteFormData) {
        IncompleteForm incompleteForm = null;
        try {
            Optional<IncompleteForm> check = incompleteFormRepository.findById(id);
            if (check.isPresent())
                incompleteForm = incompleteFormRepository.saveAndFlush(incompleteFormData);
        } catch (Exception exception) {
            System.out.println("@updateIncompleteForm Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateIncompleteForm", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return incompleteForm;
    }

    @Override
    public String deleteIncompleteForm(Integer id) {
        String status = "";
        try {
            incompleteFormRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteIncompleteForm Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteIncompleteForm", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

<<<<<<< HEAD
//    @Override
//    public ResponseEntity<Map> getUserPendingCases(String submittedby) {
//        return null;
//    }

=======
<<<<<<< HEAD
    /*@Override
    public  ResponseEntity<Map> getUserPendingCases(String submittedby){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {

            IncompleteForm  dd =  this.incompleteFormRepository.getUserPendingCases(submittedby);

            if (cc!=null){
                map.put("status_code", "200");
                map.put("results", this.incompleteFormRepository.getUserPendingCases(submittedby));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.incompleteFormRepository.getUserPendingCases(submittedby));
                map.put("errorMessage", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  new ResponseEntity<>(map,status);
    }*/
    @Override
    public ResponseEntity<Map> getUserPendingCases(String submittedby) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            List<IncompleteForm> incompleteFormsList = incompleteFormRepository.getUserPendingCases(submittedby);
            if (incompleteFormsList.isEmpty()) {
                map.put("status", 404);
                map.put("message", "Record Found!");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "Record Found!");
                map.put("data", incompleteFormsList);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@getAllIncompleteForms Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllIncompleteForms", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }





=======
>>>>>>> 11e24c0e723697ed8c8cd6df84b2ded20f26db1c
    @Override
    public ResponseEntity<Map> getUserPendingCases(String submittedby) {
        List<IncompleteForm> pendingCases = incompleteFormRepository.getUserPendingCases(submittedby);

        // Convert the list to a Map or process it as needed
        Map<String, Object> response = new HashMap<>();
        response.put("pendingCases", pendingCases);

        return ResponseEntity.ok(response);
    }


>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
}
