package com.digiplan.services;

import com.digiplan.entities.PatientHistoryEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientHistoryService {

    public ResponseEntity<Map> addPatientHistory(PatientHistoryEntity addPatientHistoryEntity);

    public ResponseEntity<Map> updatePatientHistory(Long id, PatientHistoryEntity updatePatientHistoryEntity);

    public ResponseEntity<Map> getPatientHistory(Long id);

    public ResponseEntity<Map> getPatientHistorys();

    public ResponseEntity<Map> deletePatientHistory(Long id);
}
