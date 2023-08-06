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
//import com.digiplan.entities.Image;
//import com.digiplan.repositories.ImageRepository;
//
//@SpringBootTest
//public class ImageServiceImplTests {
//
//    @InjectMocks
//    private ImageServiceImpl imageServiceImpl;
//
//    @Mock
//    private ImageRepository imageRepository;
//
//    @Test
//    public void test_getImage() {
//        Image image = new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg",
//                "download (4).jpg", "", "", "", "", "", "", "", "", "", "2103075521");
//        Optional<Image> retrievedData = Optional.of(new Image(1, 37, 92, "Karan Singh-1617608232735", "phase1",
//                "download (1).jpg", "download (4).jpg", "", "", "", "", "", "", "", "", "", "2103075521"));
//        Integer id = 1;
//        when(imageRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(imageRepository.getById(id)).thenReturn(image);
//        assertEquals(id, imageServiceImpl.getImage(id).getId());
//    }
//
//    @Test
//    public void test_getAllImages() {
//        List<Image> image = new ArrayList<>();
//        image.add(new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg", "download (4).jpg",
//                "", "", "", "", "", "", "", "", "", "2103075521"));
//        image.add(new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg", "download (4).jpg",
//                "", "", "", "", "", "", "", "", "", "2103075521"));
//        image.add(new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg", "download (4).jpg",
//                "", "", "", "", "", "", "", "", "", "2103075521"));
//        when(imageRepository.findAll()).thenReturn(image);
//        assertEquals(3, imageServiceImpl.getAllImages().size());
//    }
//
//    @Test
//    public void test_addImage() {
//        Image image = new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg",
//                "download (4).jpg", "", "", "", "", "", "", "", "", "", "2103075521");
//        when(imageRepository.saveAndFlush(image)).thenReturn(image);
//        assertEquals(image, imageServiceImpl.addImage(image));
//    }
//
//    @Test
//    public void test_updateImage() {
//        Image image = new Image(1, 34, 62, "Ms.Alena Singh-1617608232735", "phase1", "download (1).jpg",
//                "download (4).jpg", "", "", "", "", "", "", "", "", "", "2103075521");
//        Optional<Image> retrievedData = Optional.of(new Image(1, 37, 92, "Karan Singh-1617608232735", "phase1",
//                "download (1).jpg", "download (4).jpg", "", "", "", "", "", "", "", "", "", "2103075521"));
//        Integer id = 1;
//        when(imageRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(imageRepository.saveAndFlush(image)).thenReturn(image);
//        assertEquals(image, imageServiceImpl.updateImage(id, image));
//    }
//
//    @Test
//    public void test_deleteImage() {
//        Integer id = 1;
//        imageServiceImpl.deleteImage(id);
//        verify(imageRepository, times(1)).deleteById(id);
//    }
//
//}
