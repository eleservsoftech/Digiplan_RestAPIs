//package com.digiplan.servicesImpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.digiplan.entities.Gallery;
//import com.digiplan.repositories.GalleryRepository;
//
//@SpringBootTest
//public class GalleryServiceImplTests {
//
//    @InjectMocks
//    private GalleryServiceImpl galleryServiceImpl;
//
//    @Mock
//    private GalleryRepository galleryRepository;
//
//    @Test
//    public void test_getGalleryData() {
//        Gallery gallery = new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", "");
//        Optional<Gallery> retrievedData = Optional.of(new Gallery("POB01", "2013-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Technologies'}", "", ""));
//        String caseId = "POB01";
//        when(galleryRepository.findById(caseId)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(galleryRepository.getById(caseId)).thenReturn(gallery);
//        assertEquals(caseId, galleryServiceImpl.getGalleryData(caseId).getCaseId());
//    }
//
//    @Test
//    public void test_getAllGalleryData() {
//        List<Gallery> gallery = new ArrayList<>();
//        gallery.add(new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", ""));
//        gallery.add(new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", ""));
//        gallery.add(new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", ""));
//        when(galleryRepository.findAll()).thenReturn(gallery);
//        assertEquals(3, galleryServiceImpl.getAllGalleryData().size());
//    }
//
//    @Test
//    public void test_addGalleryData() {
//        Gallery gallery = new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", "");
//        when(galleryRepository.saveAndFlush(gallery)).thenReturn(gallery);
//        assertEquals(gallery, galleryServiceImpl.addGalleryData(gallery));
//    }
//
//    @Test
//    public void test_updateGalleryData() {
//        Gallery gallery = new Gallery("POB01", "2019-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies'}", "", "");
//        Optional<Gallery> retrievedData = Optional.of(new Gallery("POB01", "2013-02-02", "", "",
//                "{'date':'2018-01-19','DoctorName':'Technologies'}", "", ""));
//        String caseId = "POB01";
//        when(galleryRepository.findById(caseId)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(galleryRepository.saveAndFlush(gallery)).thenReturn(gallery);
//        assertEquals(gallery, galleryServiceImpl.updateGalleryData(caseId, gallery));
//    }
//
//    @Test
//    public void test_deleteGalleryData() {
//        String caseId = "POB01";
//        galleryServiceImpl.deleteGalleryData(caseId);
//        verify(galleryRepository, times(1)).deleteById(caseId);
//    }
//
//}
