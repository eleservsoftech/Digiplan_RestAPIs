//package com.digiplan.servicesImpl;
//
//import com.digiplan.entities.Draft;
//import com.digiplan.repositories.DraftRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class DraftServiceImplTests {
//
//    @InjectMocks
//    private DraftServiceImpl draftServiceImpl;
//
//    @Mock
//    private DraftRepository draftRepository;
//
//    @Test
//    public void test_getDraft() {
//        Draft draft = new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Amit", 1, 1);
//        Optional<Draft> retrievedData = Optional
//                .of(new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':false}}", "Suraj", 1, 1));
//        Integer id = 1;
//        when(draftRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(draftRepository.getById(id)).thenReturn(draft);
//        assertEquals(id, draftServiceImpl.getDraft(id).getId());
//    }
//
//    @Test
//    public void test_getAllDrafts() {
//        List<Draft> draft = new ArrayList<>();
//        draft.add(new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Amit", 1, 1));
//        draft.add(new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Sumit", 1, 2));
//        draft.add(new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Namit", 1, 3));
//        when(draftRepository.findAll()).thenReturn(draft);
//        assertEquals(3, draftServiceImpl.getAllDrafts().size());
//    }
//
//    @Test
//    public void test_addDraft() {
//        Draft draft = new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Amit", 1, 1);
//        when(draftRepository.saveAndFlush(draft)).thenReturn(draft);
//        assertEquals(draft, draftServiceImpl.addDraft(draft));
//    }
//
//    @Test
//    public void test_updateDraft() {
//        Draft draft = new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':true}}", "Amit", 1, 1);
//        Optional<Draft> retrievedData = Optional
//                .of(new Draft(LocalDate.now(), "{'date':'2018-07-14','CrossBite':{'No':false}}", "Suraj", 1, 1));
//        Integer id = 1;
//        when(draftRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(draftRepository.saveAndFlush(draft)).thenReturn(draft);
//        assertEquals(draft, draftServiceImpl.updateDraft(id, draft));
//    }
//
//  /*  @Test
//    public void test_deleteDraft() {
//        Integer id = 1;
//        draftServiceImpl.deleteDraft(id);
//        verify(draftRepository, times(1)).deleteById(id);
//    }*/
//
//}
