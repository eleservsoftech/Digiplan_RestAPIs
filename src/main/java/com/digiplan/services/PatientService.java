package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.Patient;

public interface PatientService {

    Patient getPatient(String caseId);

    List<Patient> getAllPatients();

    Patient addPatient(Patient patientData);

    Patient updatePatient(String caseId, Patient patientData);

    String deletePatient(String caseId);
}
