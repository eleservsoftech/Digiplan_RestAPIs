package com.digiplan.servicesImpl;

import com.digiplan.entities.PatientHistoryEntity;
import com.digiplan.repositories.PatientHistoryRepository;
import com.digiplan.services.PatientHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PatientHistoryServiceImpl implements PatientHistoryService {

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;

    @Override
    public ResponseEntity<Map> addPatientHistory(PatientHistoryEntity addPatientHistoryEntity) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            PatientHistoryEntity PatientHistoryEntityObj = patientHistoryRepository.saveAndFlush(addPatientHistoryEntity);
            map.put("status_code", "201");
            map.put("message", "Data saved successfully");
            map.put("data", PatientHistoryEntityObj);
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addPatientHistory{} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    @Override
    public ResponseEntity<Map> updatePatientHistory(Long id, PatientHistoryEntity updatePatientHistory) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<PatientHistoryEntity> isPatientHistoryEntityExist = patientHistoryRepository.findById(id);
            if (isPatientHistoryEntityExist.isPresent()) {
                PatientHistoryEntity PatientHistoryEntityObj = patientHistoryRepository.saveAndFlush(updatePatientHistory);
                map.put("status_code", "200");
                map.put("message", "Data updated successfully");
                map.put("data", PatientHistoryEntityObj);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", updatePatientHistory);
                map.put("message", "data not updated!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addPatientHistory{0} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getPatientHistory(Long id) {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (this.patientHistoryRepository.findById(id).isPresent()) {
                map.put("status_code", "200");
                map.put("results", this.patientHistoryRepository.findById(id));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.patientHistoryRepository.findById(id));
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  getPatientHistory{0} " + e.getMessage());

        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getPatientHistorys() {
        System.out.println("check patient impl123 ");
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (!this.patientHistoryRepository.findAll().isEmpty()) {
                map.put("status_code", "200");
                map.put("results", this.patientHistoryRepository.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.patientHistoryRepository.findAll());
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  getPatientHistorys{0} " + e.getMessage());

        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deletePatientHistory(Long id) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            if (this.patientHistoryRepository.findById(id).isPresent()) {
                this.patientHistoryRepository.deleteById(id);
                map.put("status_code", "200");
                map.put("results", id);
                map.put("message", "Deleted");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "204");
                map.put("results", id);
                map.put("error", "Not Deleted!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", "500");
            map.put("error", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity(map, status);
    }


}
