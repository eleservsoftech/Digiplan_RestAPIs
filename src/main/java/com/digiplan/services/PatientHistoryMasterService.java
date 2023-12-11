package com.digiplan.services;

import com.digiplan.entities.PatientHistoryMasterEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientHistoryMasterService {
    public ResponseEntity<Map> addPatientHistoryMaster(PatientHistoryMasterEntity addPatientHistoryMaster);

    public ResponseEntity<Map> updatePatientHistoryMaster(Integer action_id, PatientHistoryMasterEntity updatePatientHistoryMaster);

    public ResponseEntity<Map> getPatientMasterHistory(Integer action_id);

    public ResponseEntity<Map> getPatientHistoryMasters();

    public ResponseEntity<Map> deletePatientHistoryMaster(Integer action_id);
}
