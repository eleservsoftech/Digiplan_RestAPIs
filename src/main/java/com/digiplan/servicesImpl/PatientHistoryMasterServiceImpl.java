package com.digiplan.servicesImpl;

import com.digiplan.entities.PatientHistoryMasterEntity;
import com.digiplan.repositories.PatientHistoryMasterRepository;
import com.digiplan.services.PatientHistoryMasterService;
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
public class PatientHistoryMasterServiceImpl implements PatientHistoryMasterService {

    @Autowired
    private PatientHistoryMasterRepository patientHistoryMasterRepo;

    @Override
    public ResponseEntity<Map> addPatientHistoryMaster(PatientHistoryMasterEntity addPatientHistoryMaster) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            PatientHistoryMasterEntity PatientHistoryMasterEntityObj = patientHistoryMasterRepo.saveAndFlush(addPatientHistoryMaster);
            map.put("status_code", "201");
            map.put("message", "Data saved successfully");
            map.put("data", PatientHistoryMasterEntityObj);
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addPatientHistoryMasterEntity{0} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    @Override
    public ResponseEntity<Map> updatePatientHistoryMaster(Integer action_id, PatientHistoryMasterEntity updatePatientHistoryMaster) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<PatientHistoryMasterEntity> isPatientHistoryMasterEntityExist = patientHistoryMasterRepo.findById(action_id);
            if (isPatientHistoryMasterEntityExist.isPresent()) {
                PatientHistoryMasterEntity PatientHistoryMasterEntityObj = patientHistoryMasterRepo.saveAndFlush(updatePatientHistoryMaster);
                map.put("status_code", "200");
                map.put("message", "Data updated successfully");
                map.put("data", PatientHistoryMasterEntityObj);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", updatePatientHistoryMaster);
                map.put("message", "data not updated!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  updatePatientHistoryMaster{0} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getPatientMasterHistory(Integer action_id) {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (this.patientHistoryMasterRepo.findById(action_id).isPresent()) {
                map.put("status_code", "200");
                map.put("results", this.patientHistoryMasterRepo.findById(action_id));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.patientHistoryMasterRepo.findById(action_id));
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  getPatientMasterHistory{0} " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getPatientHistoryMasters() {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (!this.patientHistoryMasterRepo.findAll().isEmpty()) {
                map.put("status_code", "200");
                map.put("results", this.patientHistoryMasterRepo.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.patientHistoryMasterRepo.findAll());
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log getPatientHistoryMasters{0} " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deletePatientHistoryMaster(Integer action_id) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            if (this.patientHistoryMasterRepo.findById(action_id).isPresent()) {
                this.patientHistoryMasterRepo.deleteById(action_id);
                map.put("status_code", "200");
                map.put("results", action_id);
                map.put("message", "Deleted");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "204");
                map.put("results", action_id);
                map.put("error", "Not Deleted!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", "500");
            map.put("error", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  deletePatientHistoryMaster{0} " + ex.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }
}
