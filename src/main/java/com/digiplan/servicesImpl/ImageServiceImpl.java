package com.digiplan.servicesImpl;

import com.digiplan.entities.Image;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.ImageRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Environment environment;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Image getImage(Integer id) {
        Image image = null;
        try {
            Optional<Image> check = imageRepository.findById(id);
            if (check.isPresent())
                image = imageRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getImage Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getImage", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return image;
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> imagesList = null;
        try {
            imagesList = imageRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllImages Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllImages", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return imagesList;
    }

    @Override
    public ResponseEntity<Map> addImage(Image imageData) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            Image image = imageRepository.saveAndFlush(imageData);
            if(image!=null){
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", image);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@addImage Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addImage", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    @Override
    public Image updateImage(Integer id, Image imageData) {
        Image image = null;
        try {
            Optional<Image> check = imageRepository.findById(id);
            if (check.isPresent())
                image = imageRepository.saveAndFlush(imageData);
        } catch (Exception exception) {
            System.out.println("@updateImage Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateImage", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return image;
    }

    @Override
    public String deleteImage(Integer id) {
        String status = "";
        try {
            imageRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteImage Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteImage", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

    @Override
    public ResponseEntity<Map> uploadPPFFiles(Integer draftId, Integer formId, MultipartFile side, MultipartFile front, MultipartFile frontSmiling,
                                              MultipartFile rightBuccal, MultipartFile leftBuccal, MultipartFile upperOcclusial, MultipartFile lowerOcclusial,
                                              MultipartFile frontal, MultipartFile opg, MultipartFile lateralCeph, MultipartFile other,
                                              MultipartFile pdf1, MultipartFile pdf2, String caseId, String patientName) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime now = LocalDateTime.now();

            File file = new File(environment.getProperty("upload.file.path") + patientName + "-" + dtf.format(now));
            file.mkdir();
            File file1 = new File(file.getPath() + File.separator + "phase1");
            file1.mkdir();

            List<MultipartFile> filesList = Arrays.asList(side, front, frontSmiling, rightBuccal, leftBuccal, upperOcclusial,
                    lowerOcclusial, frontal, lateralCeph, other, pdf1, pdf2);

            for (MultipartFile uploadedFile : filesList) {
                if (uploadedFile.isEmpty() == false && uploadedFile.getSize() != 0) {
                    File file2 = new File(File.separator + file1 + File.separator + uploadedFile.getOriginalFilename());
                    uploadedFile.transferTo(file2);
                }
            }

            Image image = new Image();
            if (draftId != null)
                image.setDraftId(draftId);
            if (formId != null)
                image.setFormId(formId);
            image.setFolderName(file.getName());
            image.setStage(file1.getName());
            image.setSide(side.getOriginalFilename());
            image.setFront(front.getOriginalFilename());
            image.setFrontSmiling(frontSmiling.getOriginalFilename());
            image.setRightBuccal(rightBuccal.getOriginalFilename());
            image.setLeftBuccal(leftBuccal.getOriginalFilename());
            image.setUpperOcclusial(upperOcclusial.getOriginalFilename());
            image.setLowerOcclusial(lowerOcclusial.getOriginalFilename());
            image.setFrontal(frontal.getOriginalFilename());
            image.setOpg(opg.getOriginalFilename());
            image.setLateralCeph(lateralCeph.getOriginalFilename());
            image.setOther(other.getOriginalFilename());
            image.setPdf1(pdf1.getOriginalFilename());
            image.setPdf2(pdf2.getOriginalFilename());
            image.setCaseId(caseId);
            imageRepository.saveAndFlush(image);

            map.put("status", 200);
            status = HttpStatus.OK;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("@uploadFiles Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "uploadFiles", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

}
