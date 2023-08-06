package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.Diagnosis;

public interface DiagnosisService {

    Diagnosis getDiagnosisData(String id);

    List<Diagnosis> getAllDiagnosisData();

    Diagnosis addDiagnosisData(Diagnosis diagnosisData);

    Diagnosis updateDiagnosisData(String id, Diagnosis diagnosisData);

    String deleteDiagnosisData(String id);

}
