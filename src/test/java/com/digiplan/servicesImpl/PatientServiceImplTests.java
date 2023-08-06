package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.Patient;
import com.digiplan.repositories.PatientRepository;

@SpringBootTest
public class PatientServiceImplTests {

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void test_getPatient() {
        Patient patient = new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1",
                "Dental Case");
        Optional<Patient> retrievedData = Optional
                .of(new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1", "Dental Case"));
        String caseId = "2657656567";
        when(patientRepository.findById(caseId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(patientRepository.getById(caseId)).thenReturn(patient);
        assertEquals(caseId, patientServiceImpl.getPatient(caseId).getCaseId());
    }

    @Test
    public void test_getAllPatients() {
        List<Patient> patient = new ArrayList<>();
        patient.add(new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1", "Dental Case"));
        patient.add(new Patient("2657656545", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1", "Dental Case"));
        patient.add(new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1", "Dental Case"));
        when(patientRepository.findAll()).thenReturn(patient);
        assertEquals(3, patientServiceImpl.getAllPatients().size());
    }

    @Test
    public void test_addPatient() {
        Patient patient = new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1",
                "Dental Case");
        when(patientRepository.saveAndFlush(patient)).thenReturn(patient);
        assertEquals(patient, patientServiceImpl.addPatient(patient));
    }

    @Test
    public void test_updatePatient() {
        Patient patient = new Patient("2657656567", "Shilpa", "Female", new Date(1960 - 12 - 22), "30", "1",
                "Dental Case");
        Optional<Patient> retrievedData = Optional
                .of(new Patient("2657656567", "Karan", "Male", new Date(1960 - 12 - 22), "36", "1", "Dental Case"));
        String caseId = "2657656567";
        when(patientRepository.findById(caseId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(patientRepository.saveAndFlush(patient)).thenReturn(patient);
        assertEquals(patient, patientServiceImpl.updatePatient(caseId, patient));
    }

    @Test
    public void test_deletePatient() {
        String caseId = "2657656567";
        patientServiceImpl.deletePatient(caseId);
        verify(patientRepository, times(1)).deleteById(caseId);
    }

}
