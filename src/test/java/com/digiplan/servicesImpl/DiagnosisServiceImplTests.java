package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.Diagnosis;
import com.digiplan.repositories.DiagnosisRepository;

@SpringBootTest
public class DiagnosisServiceImplTests {

    @InjectMocks
    private DiagnosisServiceImpl diagnosisServiceImpl;

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @Test
    public void test_getDiagnosisData() {

        Diagnosis diagnosis = new Diagnosis(1242, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil");
        Optional<Diagnosis> retrievedData = Optional.of(new Diagnosis(1242,
                "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242", LocalDateTime.now(), "support1",
                "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00, 1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00,
                0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil", "nil", "Class1", "", "", "nil"));
        String id = "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242";
        when(diagnosisRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(diagnosisRepository.getById(id)).thenReturn(diagnosis);
        assertEquals(id, diagnosisServiceImpl.getDiagnosisData(id).getId());
    }

    @Test
    public void test_getAllDiagnosisData() {
        List<Diagnosis> diagnosis = new ArrayList<>();
        diagnosis.add(new Diagnosis(1242, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil"));
        diagnosis.add(new Diagnosis(1243, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1243",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil"));
        diagnosis.add(new Diagnosis(1244, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1244",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil"));
        when(diagnosisRepository.findAll()).thenReturn(diagnosis);
        assertEquals(3, diagnosisServiceImpl.getAllDiagnosisData().size());
    }

    @Test
    public void test_addDiagnosisData() {
        Diagnosis diagnosis = new Diagnosis(1242, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil");
        when(diagnosisRepository.saveAndFlush(diagnosis)).thenReturn(diagnosis);
        assertEquals(diagnosis, diagnosisServiceImpl.addDiagnosisData(diagnosis));
    }

    @Test
    public void test_updateDiagnosisData() {
        Diagnosis diagnosis = new Diagnosis(1242, "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242",
                LocalDateTime.now(), "support1", "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00,
                1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00, 0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil",
                "nil", "Class1", "", "", "nil");
        Optional<Diagnosis> retrievedData = Optional.of(new Diagnosis(1242,
                "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242", LocalDateTime.now(), "support1",
                "Mr.Sarath", 1, 0, 1, 0, 0, 0, 0, "Class1", "", "Class1", "", 1.00, 1.00, 1, 2, 1, 0, 0, 0, 0.00, 0.00,
                0.00, 0.00, "Normal", "Normal", "nil", "Class1", "", "", "nil", "nil", "Class1", "", "", "nil"));
        String id = "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242";
        when(diagnosisRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(diagnosisRepository.saveAndFlush(diagnosis)).thenReturn(diagnosis);
        assertEquals(diagnosis, diagnosisServiceImpl.updateDiagnosisData(id, diagnosis));
    }

    @Test
    public void test_deleteDiagnosisData() {
        String id = "01/05/2021 07:39:24_06713dbc-28e9-485e-bd87-fc0da943d31c_1242";
        diagnosisServiceImpl.deleteDiagnosisData(id);
        verify(diagnosisRepository, times(1)).deleteById(id);
    }

}
