//package com.digiplan.servicesImpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.digiplan.entities.IncompleteForm;
//import com.digiplan.repositories.IncompleteFormRepository;
//
//@SpringBootTest
//public class IncompleteFormServiceImplTests {
//
//    @InjectMocks
//    private IncompleteFormServiceImpl incompleteFormServiceImpl;
//
//    @Mock
//    private IncompleteFormRepository incompleteFormRepository;
//
//    @Test
//    public void test_getIncompleteForm() {
//        IncompleteForm incompleteForm = new IncompleteForm(1, LocalDate.now(),
//                "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan", "", "Vishnu", "N", "Suraj", "100");
//        Optional<IncompleteForm> retrievedData = Optional.of(new IncompleteForm(1, LocalDate.now(),
//                "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan", "", "Vishnu", "N", "Suraj", "100"));
//        Integer id = 1;
//        when(incompleteFormRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(incompleteFormRepository.getById(id)).thenReturn(incompleteForm);
//        assertEquals(id, incompleteFormServiceImpl.getIncompleteForm(id).getId());
//    }
//
//    @Test
//    public void test_getAllIncompleteForms() {
//        List<IncompleteForm> incompleteForm = new ArrayList<>();
//        incompleteForm.add(new IncompleteForm(1, LocalDate.now(), "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan",
//                "", "Vishnu", "N", "Suraj", "100"));
//        incompleteForm.add(new IncompleteForm(1, LocalDate.now(), "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan",
//                "", "Vishnu", "N", "Suraj", "100"));
//        incompleteForm.add(new IncompleteForm(1, LocalDate.now(), "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan",
//                "", "Vishnu", "N", "Suraj", "100"));
//        when(incompleteFormRepository.findAll()).thenReturn(incompleteForm);
//        assertEquals(3, incompleteFormServiceImpl.getAllIncompleteForms().size());
//    }
//
//    @Test
//    public void test_addIncompleteForm() {
//        IncompleteForm incompleteForm = new IncompleteForm(1, LocalDate.now(),
//                "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan", "", "Vishnu", "N", "Suraj", "100");
//        when(incompleteFormRepository.saveAndFlush(incompleteForm)).thenReturn(incompleteForm);
//        assertEquals(incompleteForm, incompleteFormServiceImpl.addIncompleteForm(incompleteForm));
//    }
//
//    @Test
//    public void test_updateIncompleteForm() {
//        IncompleteForm incompleteForm = new IncompleteForm(1, LocalDate.now(),
//                "{'date':'2019-03-28','DoctorName':'Suraj'}", "Suraj", "", "Sudhi", "N", "Arjun", "90");
//        Optional<IncompleteForm> retrievedData = Optional.of(new IncompleteForm(1, LocalDate.now(),
//                "{'date':'2019-03-28','DoctorName':'Suraj'}", "Karan", "", "Vishnu", "N", "Suraj", "100"));
//        Integer id = 1;
//        when(incompleteFormRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(incompleteFormRepository.saveAndFlush(incompleteForm)).thenReturn(incompleteForm);
//        assertEquals(incompleteForm, incompleteFormServiceImpl.updateIncompleteForm(id, incompleteForm));
//    }
//
//    @Test
//    public void test_deleteIncompleteForm() {
//        Integer id = 1;
//        incompleteFormServiceImpl.deleteIncompleteForm(id);
//        verify(incompleteFormRepository, times(1)).deleteById(id);
//    }
//
//}
