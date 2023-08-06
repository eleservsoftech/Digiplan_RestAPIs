package com.digiplan.services;

import com.digiplan.entities.Doctor;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DoctorService {

	public Doctor getDoctor(String caseId);

	public ResponseEntity<Map> getAllDoctors(String searchDoctor);

	public Doctor addDoctor(Doctor doctorData);

	public Doctor updateDoctor(String caseId, Doctor doctorData);

	public String deleteDoctor(String caseId);
}
