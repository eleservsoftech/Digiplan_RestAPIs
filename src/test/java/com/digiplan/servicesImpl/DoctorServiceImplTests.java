package com.digiplan.servicesImpl;

import com.digiplan.entities.Doctor;
import com.digiplan.repositories.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DoctorServiceImplTests {

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @Mock
    private DoctorRepository doctorRepository;

    @Test
    public void test_getDoctor() {
        Doctor doctor = new Doctor("1001001000", "Karan", "9999393993", "Himanshu", "9839587467", "R-1990/B2", "09",
                "demo@gmail.com");
        Optional<Doctor> retrievedData = Optional.of(new Doctor("1001001000", "Suraj", "9999393993", "Himanshu",
                "9839587467", "R-1990/B2", "03", "demo@gmail.com"));
        String caseId = "1001001000";
        when(doctorRepository.findById(caseId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(doctorRepository.getById(caseId)).thenReturn(doctor);
        assertEquals(caseId, doctorServiceImpl.getDoctor(caseId).getCaseId());
    }

//    @Test
//    public void test_getAllDoctors() {
//        List<Doctor> doctor = new ArrayList<>();
//        doctor.add(new Doctor("1001001000", "Karan", "9999393993", "Himanshu", "9839587467", "R-1990/B1", "09",
//                "demo1@gmail.com"));
//        doctor.add(new Doctor("1001001001", "Maran", "9999393994", "Timanshu", "9839587468", "R-1990/B2", "08",
//                "demo2@gmail.com"));
//        doctor.add(new Doctor("1001001002", "Caran", "9999393995", "Vimanshu", "9839587469", "R-1990/B3", "07",
//                "demo3@gmail.com"));
//        when(doctorRepository.findAll()).thenReturn(doctor);
//        assertEquals(3, doctorServiceImpl.getAllDoctors().size());
//    }

    @Test
    public void test_addDoctor() {
        Doctor doctor = new Doctor("1001001000", "Karan", "9999393993", "Himanshu", "9839587467", "R-1990/B2", "09",
                "demo@gmail.com");
        when(doctorRepository.saveAndFlush(doctor)).thenReturn(doctor);
        assertEquals(doctor, doctorServiceImpl.addDoctor(doctor));
    }

    @Test
    public void test_updateDoctor() {
        Doctor doctor = new Doctor("1001001000", "Karan", "9999393993", "Himanshu", "9839587467", "R-1990/B2", "09",
                "demo@gmail.com");
        Optional<Doctor> retrievedData = Optional.of(new Doctor("1001001000", "Suraj", "9999393993", "Himanshu",
                "9839587467", "R-1990/B2", "03", "demo@gmail.com"));
        String caseId = "1001001000";
        when(doctorRepository.findById(caseId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(doctorRepository.saveAndFlush(doctor)).thenReturn(doctor);
        assertEquals(doctor, doctorServiceImpl.updateDoctor(caseId, doctor));
    }

    @Test
    public void test_deleteDoctor() {
        String caseId = "1001001000";
        doctorServiceImpl.deleteDoctor(caseId);
        verify(doctorRepository, times(1)).deleteById(caseId);
    }

}
