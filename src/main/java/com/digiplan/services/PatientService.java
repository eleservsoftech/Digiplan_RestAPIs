package com.digiplan.services;

import java.util.List;
import java.util.Map;

import com.digiplan.entities.Patient;
import org.springframework.http.ResponseEntity;

public interface PatientService {

    Patient getPatient(String caseId);

    List<Patient> getAllPatients();

    public ResponseEntity<Map> addPatient(Patient addPatient);

    Patient updatePatient(String caseId, Patient patientData);

    String deletePatient(String caseId);
}
