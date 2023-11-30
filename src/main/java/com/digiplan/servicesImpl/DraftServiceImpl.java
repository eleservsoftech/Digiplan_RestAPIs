package com.digiplan.servicesImpl;

import com.digiplan.entities.Draft;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.DraftRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DraftServiceImpl implements DraftService {

    @Autowired
    private DraftRepository draftRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

//    @Value("${file.upload.dir}")
//    private String uploadDir;
//
//    private final String baseDirectory = "D:\\DRAFTIMAGES";

    @Override
    public Draft getDraft(Integer id) {
        Draft draft = null;
        try {
            Optional<Draft> check = draftRepository.findById(id);
            if (check.isPresent())
                draft = draftRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

    @Override
    public List<Draft> getAllDrafts() {
        List<Draft> draftsList = null;
        try {
            draftsList = draftRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllDrafts Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllDrafts", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draftsList;
    }

    @Override
    public Draft addDraft(Draft draftData) {
        Draft draft = null;
        try {
            draft = draftRepository.saveAndFlush(draftData);
        } catch (Exception exception) {
            System.out.println("@addDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

    //testing aman
//    @Override
//    public Draft addDraft(Draft draftData, MultipartFile image1, MultipartFile image2, MultipartFile image3,
//                          MultipartFile image4, MultipartFile image5, MultipartFile image6,  MultipartFile image7,  MultipartFile image8, MultipartFile pdf1, String file_path) {
//        try {
//            String basePath = baseDirectory + File.separator + draftData.getFile_path() + "_" + UUID.randomUUID();
//
//            // Save the 6 images
//            String image1FileName = saveUploadedFile(image1, basePath, "Image1");
//            String image2FileName = saveUploadedFile(image2, basePath, "Image2");
//            String image3FileName = saveUploadedFile(image3, basePath, "Image3");
//            String image4FileName = saveUploadedFile(image4, basePath, "Image4");
//            String image5FileName = saveUploadedFile(image5, basePath, "Image5");
//            String image6FileName = saveUploadedFile(image6, basePath, "Image6");
//            String image7FileName = saveUploadedFile(image7, basePath, "Image7");
//            String image8FileName = saveUploadedFile(image8, basePath, "Image8");
//
//            // Save the PDF
//            String pdfFileName = saveUploadedFile(pdf1, basePath, "PDF");
//
//            draftData.setImage1(image1FileName);
//            draftData.setImage2(image2FileName);
//            draftData.setImage3(image3FileName);
//            draftData.setImage4(image4FileName);
//            draftData.setImage5(image5FileName);
//            draftData.setImage6(image6FileName);
    //        draftData.setImage7(image7FileName);
//            draftData.setImage8(image8FileName);

//            draftData.setPdf1(pdfFileName);
//
//            return draftRepository.save(draftData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error while uploading and saving details: " + e.getMessage());
//        }
//    }
//
//    private String saveUploadedFile(MultipartFile file, String basePath, String fileNameOriginal) {
//        if (file != null && !file.isEmpty()) {
//            try {
//                File f = new File(basePath);
//                if (!f.exists()) {
//                    f.mkdirs();
//                }
//                String fileName = UUID.randomUUID() + "_" + fileNameOriginal + "_" + file.getOriginalFilename();
//                String filePath = Paths.get(basePath, fileName).toString();
//
//                file.transferTo(new File(filePath));
//
//                return fileName;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
    //

    @Override
    public Draft updateDraft(Integer id, Draft draftData) {
        Draft draft = null;
        try {
            Optional<Draft> check = draftRepository.findById(id);
            if (check.isPresent())
                draft = draftRepository.saveAndFlush(draftData);
        } catch (Exception exception) {
            System.out.println("@updateDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

   /* @Override
    public String deleteDraft(Integer id) {
        log.info("@Start deleteDraft");
        String status = "";
        try {
            draftRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            log.error("Exception = " + exception);
        }
        return status;
    } */

    @Override
    public ResponseEntity<Map> viewDrafts(Draft draftData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<Draft> drafts = new ArrayList<>();
            List<Draft> draftList = draftRepository.findAll();
            for (Draft draft : draftList) {
                if (draft.getSavedBy().equalsIgnoreCase(draftData.getSavedBy()) && draft.getIsActive() == 1) {
                    drafts.add(draft);
                }
            }
            if (drafts.isEmpty()) {
                map.put("status", 404);
                map.put("message", "No Record Found!");
                map.put("response", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "Record Found!");
                map.put("response", drafts);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@viewDrafts Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "viewDrafts", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deleteDraft(String draftId) {
        HttpStatus status = null;
        Map<String, Object> map = new HashMap();
        try {
            Optional<Draft> check = draftRepository.findById(Integer.parseInt(draftId));
            if (check.isPresent()) {
                Draft draft = draftRepository.findById(Integer.parseInt(draftId)).get();
                if (draft.getIsActive() == 1) {
                    draft.setIsActive(0);
                    draftRepository.saveAndFlush(draft);
                    map.put("message", "Record Deleted");
                    map.put("status", 200);
                    status = HttpStatus.OK;
                } else {
                    map.put("message", "Record Not Found");
                    map.put("status", 404);
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("message", "Record Not Found");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@deleteDraft Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("message", "Internal Server Error");
            map.put("status", 500);
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

}
