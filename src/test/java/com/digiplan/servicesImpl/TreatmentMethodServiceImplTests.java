package com.digiplan.servicesImpl;

import com.digiplan.entities.TreatmentMethod;
import com.digiplan.repositories.TreatmentMethodRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TreatmentMethodServiceImplTests {

    @InjectMocks
    private TreatmentMethodServiceImpl treatmentMethodServiceImpl;

    @Mock
    private TreatmentMethodRepository treatmentMethodRepository;

    @Test
    public void test_getTreatmentMethod() {
        TreatmentMethod treatmentMethod = new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Optional<TreatmentMethod> retrievedData = Optional.of(new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        String treatmentMethodId = "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706";
        when(treatmentMethodRepository.findById(treatmentMethodId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(treatmentMethodRepository.getById(treatmentMethodId)).thenReturn(treatmentMethod);
        assertEquals(treatmentMethodId, treatmentMethodServiceImpl.getTreatmentMethodData(treatmentMethodId).getId());
    }

    @Test
    public void test_getAllQueries() {
        List<TreatmentMethod> treatmentMethod = new ArrayList<>();
        treatmentMethod.add(new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        treatmentMethod.add(new TreatmentMethod(1704, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        treatmentMethod.add(new TreatmentMethod(1705, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        when(treatmentMethodRepository.findAll()).thenReturn(treatmentMethod);
        assertEquals(3, treatmentMethodServiceImpl.getAllTreatmentMethodData().size());
    }

    @Test
    public void test_addTreatmentMethod() {
        TreatmentMethod treatmentMethod = new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        when(treatmentMethodRepository.saveAndFlush(treatmentMethod)).thenReturn(treatmentMethod);
        assertEquals(treatmentMethod, treatmentMethodServiceImpl.addTreatmentMethodData(treatmentMethod));
    }

    @Test
    public void test_updateTreatmentMethod() {
        TreatmentMethod treatmentMethod = new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Optional<TreatmentMethod> retrievedData = Optional.of(new TreatmentMethod(1706, LocalDate.now(), "dreric", "YASHIKA", "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706", LocalDate.now(), "dreric", "YASHIKA", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        String treatmentMethodId = "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706";
        when(treatmentMethodRepository.findById(treatmentMethodId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(treatmentMethodRepository.saveAndFlush(treatmentMethod)).thenReturn(treatmentMethod);
        assertEquals(treatmentMethod, treatmentMethodServiceImpl.updateTreatmentMethodData(treatmentMethodId, treatmentMethod));
    }

    @Test
    public void test_deleteTreatmentMethod() {
        String treatmentMethodId = "02/12/2021 11:27:06_96168a36-c2ac-42da-9deb-aa67e207cfe0_1706";
        treatmentMethodServiceImpl.deleteTreatmentMethodData(treatmentMethodId);
        verify(treatmentMethodRepository, times(1)).deleteById(treatmentMethodId);
    }

}
