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
import java.nio.file.Files;
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
    @Autowired
    private Environment env;

//    @Override
//    public Image getImage(Integer id) {
//        Image image = null;
//        try {
//            Optional<Image> check = imageRepository.findById(id);
//            if (check.isPresent())
//                image = imageRepository.getById(id);
//        } catch (Exception exception) {
//            System.out.println("@getImage Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getImage", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//        }
//        return image;
//    }


    @Override
    public ResponseEntity<Map<String, Object>> getImage(Integer id) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Optional<Image> imageOptional = imageRepository.findById(id);

            if (imageOptional.isPresent()) {
                Image imagedata = imageOptional.get();
                Map<String, Object> imageDetailsData = new LinkedHashMap<>();

                imageDetailsData.put("id", imagedata.getId());
                imageDetailsData.put("draftId", imagedata.getDraftId());
                imageDetailsData.put("formId", imagedata.getFormId());
                imageDetailsData.put("folderName", imagedata.getFolderName());
                imageDetailsData.put("stage", imagedata.getStage());
                imageDetailsData.put("side", imagedata.getSide());
                imageDetailsData.put("front", imagedata.getFront());
                imageDetailsData.put("frontSmiling", imagedata.getFrontSmiling());
                imageDetailsData.put("rightBuccal", imagedata.getRightBuccal());
                imageDetailsData.put("leftBuccal", imagedata.getLeftBuccal());
                imageDetailsData.put("upperOcclusial", imagedata.getUpperOcclusial());
                imageDetailsData.put("lowerOcclusial", imagedata.getLowerOcclusial());
                imageDetailsData.put("frontal", imagedata.getFrontal());
                imageDetailsData.put("opg", imagedata.getOpg());
                imageDetailsData.put("lateralCeph", imagedata.getLateralCeph());
                imageDetailsData.put("other", imagedata.getOther());
                imageDetailsData.put("pdf1", imagedata.getPdf1());
                imageDetailsData.put("pdf2", imagedata.getPdf2());
                imageDetailsData.put("caseId", imagedata.getCaseId());

                String folderName = imagedata.getFolderName();
                String phase1 = imagedata.getStage();
                String folderPath = env.getProperty("file.uploads.location") + folderName + "/phase1";
                System.out.println("folderPath: " + folderPath);

                File folder = new File(folderPath);

                if (folder.exists() && folder.isDirectory()) {
                    File[] photosList = folder.listFiles();
                    if (photosList != null) {
                        List<Map<String, Object>> imageList = new ArrayList<>();

                        for (File photo : photosList) {
                            if (photo.isFile()) {
                                byte[] arr = Files.readAllBytes(photo.toPath());
                                Map<String, Object> imageData = new HashMap<>();
                                imageData.put("filename", photo.getName());
                                imageData.put("contentType", "image/jpeg");
                                imageData.put("data", Base64.getEncoder().encodeToString(arr));
                                imageList.add(imageData);
                            }
                        }

                        imageDetailsData.put("images", imageList);
                    }
                }

                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");
                map.put("data", imageDetailsData);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "No CaseBookingUploadDetails record found for the specified case_booking_id");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getImagebyDraftId(Integer draftId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Optional<Image> imageOptional = Optional.ofNullable(imageRepository.findByDraftId(draftId));


            if (imageOptional.isPresent()) {
                Image imagedata = imageOptional.get();
                Map<String, Object> imageDetailsData = new LinkedHashMap<>();

                imageDetailsData.put("id", imagedata.getId());
                imageDetailsData.put("draftId", imagedata.getDraftId());
                imageDetailsData.put("formId", imagedata.getFormId());
                imageDetailsData.put("folderName", imagedata.getFolderName());
                imageDetailsData.put("stage", imagedata.getStage());
                imageDetailsData.put("side", imagedata.getSide());
                imageDetailsData.put("front", imagedata.getFront());
                imageDetailsData.put("frontSmiling", imagedata.getFrontSmiling());
                imageDetailsData.put("rightBuccal", imagedata.getRightBuccal());
                imageDetailsData.put("leftBuccal", imagedata.getLeftBuccal());
                imageDetailsData.put("upperOcclusial", imagedata.getUpperOcclusial());
                imageDetailsData.put("lowerOcclusial", imagedata.getLowerOcclusial());
                imageDetailsData.put("frontal", imagedata.getFrontal());
                imageDetailsData.put("opg", imagedata.getOpg());
                imageDetailsData.put("lateralCeph", imagedata.getLateralCeph());
                imageDetailsData.put("other", imagedata.getOther());
                imageDetailsData.put("pdf1", imagedata.getPdf1());
                imageDetailsData.put("pdf2", imagedata.getPdf2());
                imageDetailsData.put("caseId", imagedata.getCaseId());

                String folderName = imagedata.getFolderName();
                String phase1 = imagedata.getStage();
                String folderPath = env.getProperty("file.uploads.location") + folderName + "/phase1";
                System.out.println("folderPath: " + folderPath);

                File folder = new File(folderPath);

                if (folder.exists() && folder.isDirectory()) {
//                    System.out.println("if1");
                    File[] photosList = folder.listFiles();
                    if (photosList != null) {

                        List<Map<String, Object>> imageList = new ArrayList<>();

                        for (File photo : photosList) {

                            if (photo.isFile()) {

                                byte[] arr = Files.readAllBytes(photo.toPath());

                                Map<String, Object> imageData = new HashMap<>();

                                imageData.put("filename", photo.getName());
                                imageData.put("contentType", "image/jpeg");
                                imageData.put("data", Base64.getEncoder().encodeToString(arr));
                                imageList.add(imageData);
                            }
                        }

                        imageDetailsData.put("images", imageList);
                    }
                }

                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");
                map.put("data", imageDetailsData);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "No CaseBookingUploadDetails record found for the specified case_booking_id");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getImagebyFormId(Integer formId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Optional<Image> imageOptional = Optional.ofNullable(imageRepository.findByFormId(formId));


            if (imageOptional.isPresent()) {
                Image imagedata = imageOptional.get();
                Map<String, Object> imageDetailsData = new LinkedHashMap<>();

                imageDetailsData.put("id", imagedata.getId());
                imageDetailsData.put("draftId", imagedata.getDraftId());
                imageDetailsData.put("formId", imagedata.getFormId());
                imageDetailsData.put("folderName", imagedata.getFolderName());
                imageDetailsData.put("stage", imagedata.getStage());
                imageDetailsData.put("side", imagedata.getSide());
                imageDetailsData.put("front", imagedata.getFront());
                imageDetailsData.put("frontSmiling", imagedata.getFrontSmiling());
                imageDetailsData.put("rightBuccal", imagedata.getRightBuccal());
                imageDetailsData.put("leftBuccal", imagedata.getLeftBuccal());
                imageDetailsData.put("upperOcclusial", imagedata.getUpperOcclusial());
                imageDetailsData.put("lowerOcclusial", imagedata.getLowerOcclusial());
                imageDetailsData.put("frontal", imagedata.getFrontal());
                imageDetailsData.put("opg", imagedata.getOpg());
                imageDetailsData.put("lateralCeph", imagedata.getLateralCeph());
                imageDetailsData.put("other", imagedata.getOther());
                imageDetailsData.put("pdf1", imagedata.getPdf1());
                imageDetailsData.put("pdf2", imagedata.getPdf2());
                imageDetailsData.put("caseId", imagedata.getCaseId());

                String folderName = imagedata.getFolderName();
                String phase1 = imagedata.getStage();
                String folderPath = env.getProperty("file.uploads.location") + folderName + "/phase1";
                System.out.println("folderPath: " + folderPath);

                File folder = new File(folderPath);

                if (folder.exists() && folder.isDirectory()) {
//                    System.out.println("if1");
                    File[] photosList = folder.listFiles();
                    if (photosList != null) {

                        List<Map<String, Object>> imageList = new ArrayList<>();

                        for (File photo : photosList) {

                            if (photo.isFile()) {

                                byte[] arr = Files.readAllBytes(photo.toPath());

                                Map<String, Object> imageData = new HashMap<>();

                                imageData.put("filename", photo.getName());
                                imageData.put("contentType", "image/jpeg");
                                imageData.put("data", Base64.getEncoder().encodeToString(arr));
                                imageList.add(imageData);
                            }
                        }

                        imageDetailsData.put("images", imageList);
                    }
                }

                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");
                map.put("data", imageDetailsData);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "No CaseBookingUploadDetails record found for the specified case_booking_id");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getImagebyCaseId(String caseId, Integer id) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Optional<Image> imageOptional = Optional.ofNullable(imageRepository.findByCaseIdAndId(caseId,id));


            if (imageOptional.isPresent()) {
                Image imagedata = imageOptional.get();
                Map<String, Object> imageDetailsData = new LinkedHashMap<>();

                imageDetailsData.put("id", imagedata.getId());
                imageDetailsData.put("draftId", imagedata.getDraftId());
                imageDetailsData.put("formId", imagedata.getFormId());
                imageDetailsData.put("folderName", imagedata.getFolderName());
                imageDetailsData.put("stage", imagedata.getStage());
                imageDetailsData.put("side", imagedata.getSide());
                imageDetailsData.put("front", imagedata.getFront());
                imageDetailsData.put("frontSmiling", imagedata.getFrontSmiling());
                imageDetailsData.put("rightBuccal", imagedata.getRightBuccal());
                imageDetailsData.put("leftBuccal", imagedata.getLeftBuccal());
                imageDetailsData.put("upperOcclusial", imagedata.getUpperOcclusial());
                imageDetailsData.put("lowerOcclusial", imagedata.getLowerOcclusial());
                imageDetailsData.put("frontal", imagedata.getFrontal());
                imageDetailsData.put("opg", imagedata.getOpg());
                imageDetailsData.put("lateralCeph", imagedata.getLateralCeph());
                imageDetailsData.put("other", imagedata.getOther());
                imageDetailsData.put("pdf1", imagedata.getPdf1());
                imageDetailsData.put("pdf2", imagedata.getPdf2());
                imageDetailsData.put("caseId", imagedata.getCaseId());

                String folderName = imagedata.getFolderName();
                String phase1 = imagedata.getStage();
                String folderPath = env.getProperty("file.uploads.location") + folderName + "/phase1";
                System.out.println("folderPath: " + folderPath);

                File folder = new File(folderPath);

                if (folder.exists() && folder.isDirectory()) {
//                    System.out.println("if1");
                    File[] photosList = folder.listFiles();
                    if (photosList != null) {

                        List<Map<String, Object>> imageList = new ArrayList<>();

                        for (File photo : photosList) {

                            if (photo.isFile()) {

                                byte[] arr = Files.readAllBytes(photo.toPath());

                                Map<String, Object> imageData = new HashMap<>();

                                imageData.put("filename", photo.getName());
                                imageData.put("contentType", "image/jpeg");
                                imageData.put("data", Base64.getEncoder().encodeToString(arr));
                                imageList.add(imageData);
                            }
                        }

                        imageDetailsData.put("images", imageList);
                    }
                }

                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");
                map.put("data", imageDetailsData);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "No CaseBookingUploadDetails record found for the specified case_booking_id");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }

        return new ResponseEntity<>(map, status);
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
            if (image != null) {
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

//    @Override
//    public ResponseEntity<Map> uploadPPFFiles(Integer draftId, Integer formId, MultipartFile side, MultipartFile front, MultipartFile frontSmiling,
//                                              MultipartFile rightBuccal, MultipartFile leftBuccal, MultipartFile upperOcclusial, MultipartFile lowerOcclusial,
//                                              MultipartFile frontal, MultipartFile opg, MultipartFile lateralCeph, MultipartFile other,
//                                              MultipartFile pdf1, MultipartFile pdf2, String caseId, String patientName) {
//        Map map = new HashMap();
//        HttpStatus status = null;
//        try {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//            LocalDateTime now = LocalDateTime.now();
//
//            File file = new File(environment.getProperty("upload.file.path") + patientName + "-" + dtf.format(now));
//            file.mkdir();
//            File file1 = new File(file.getPath() + File.separator + "phase1");
//            file1.mkdir();
//
//            List<MultipartFile> filesList = Arrays.asList(side, front, frontSmiling, rightBuccal, leftBuccal, upperOcclusial,
//                    lowerOcclusial, frontal, lateralCeph,opg, other, pdf1, pdf2);
//
//            for (MultipartFile uploadedFile : filesList) {
//                if (uploadedFile.isEmpty() == false && uploadedFile.getSize() != 0) {
//                    File file2 = new File(File.separator + file1 + File.separator + uploadedFile.getOriginalFilename());
//                    uploadedFile.transferTo(file2);
//                }
//            }
//
//            Image image = new Image();
//            if (draftId != null)
//                image.setDraftId(draftId);
//            if (formId != null)
//                image.setFormId(formId);
//            image.setFolderName(file.getName());
//            image.setStage(file1.getName());
//            image.setSide(side.getOriginalFilename());
//            image.setFront(front.getOriginalFilename());
//            image.setFrontSmiling(frontSmiling.getOriginalFilename());
//            image.setRightBuccal(rightBuccal.getOriginalFilename());
//            image.setLeftBuccal(leftBuccal.getOriginalFilename());
//            image.setUpperOcclusial(upperOcclusial.getOriginalFilename());
//            image.setLowerOcclusial(lowerOcclusial.getOriginalFilename());
//            image.setFrontal(frontal.getOriginalFilename());
//            image.setOpg(opg.getOriginalFilename());
//            image.setLateralCeph(lateralCeph.getOriginalFilename());
//            image.setOther(other.getOriginalFilename());
//            image.setPdf1(pdf1.getOriginalFilename());
//            image.setPdf2(pdf2.getOriginalFilename());
//            image.setCaseId(caseId);
//            imageRepository.saveAndFlush(image);
//
//            map.put("status", 200);
//            status = HttpStatus.OK;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            System.out.println("@uploadFiles Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "uploadFiles", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//            map.put("status", 500);
//            map.put("message", "Internal Server Error");
//            map.put("error", exception.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(map, status);
//    }


    @Override
    public ResponseEntity<Map> uploadPPFFiles(Integer draftId, Integer formId, MultipartFile side, MultipartFile front, MultipartFile frontSmiling,
                                              MultipartFile rightBuccal, MultipartFile leftBuccal, MultipartFile upperOcclusial, MultipartFile lowerOcclusial,
                                              MultipartFile frontal, MultipartFile opg, MultipartFile lateralCeph, MultipartFile other,
                                              MultipartFile pdf1, MultipartFile pdf2, String caseId, String patientName) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            LocalDateTime now = LocalDateTime.now();

            File file = new File(environment.getProperty("upload.file.path") + patientName + "-" + dtf.format(now));
            file.mkdir();

            File file1 = new File(file.getPath() + File.separator + "phase1");
            file1.mkdir();

            List<MultipartFile> filesList = Arrays.asList(side, front, frontSmiling, rightBuccal, leftBuccal, upperOcclusial,
                    lowerOcclusial, frontal, lateralCeph, opg, other, pdf1, pdf2);

            for (MultipartFile uploadedFile : filesList) {
                if (uploadedFile != null && !uploadedFile.isEmpty() && uploadedFile.getSize() > 0) {
                    File file2 = new File(file1.getPath() + File.separator + uploadedFile.getOriginalFilename());
                    uploadedFile.transferTo(file2);
                }
            }

            Image image = new Image();
            if (draftId != null) {
                image.setDraftId(draftId);
            }
            if (formId != null) {
                image.setFormId(formId);
            }

            image.setFolderName(file.getName());
            image.setStage(file1.getName());
            image.setSide(side != null ? side.getOriginalFilename() : "");
            image.setFront(front != null ? front.getOriginalFilename() : "");
            image.setFrontSmiling(frontSmiling != null ? frontSmiling.getOriginalFilename() : "");
            image.setRightBuccal(rightBuccal != null ? rightBuccal.getOriginalFilename() : "");
            image.setLeftBuccal(leftBuccal != null ? leftBuccal.getOriginalFilename() : "");
            image.setUpperOcclusial(upperOcclusial != null ? upperOcclusial.getOriginalFilename() : "");
            image.setLowerOcclusial(lowerOcclusial != null ? lowerOcclusial.getOriginalFilename() : "");
            image.setFrontal(frontal != null ? frontal.getOriginalFilename() : "");
            image.setOpg(opg != null ? opg.getOriginalFilename() : "");
            image.setLateralCeph(lateralCeph != null ? lateralCeph.getOriginalFilename() : "");
            image.setOther(other != null ? other.getOriginalFilename() : "");
            image.setPdf1(pdf1 != null ? pdf1.getOriginalFilename() : "");
            image.setPdf2(pdf2 != null ? pdf2.getOriginalFilename() : "");
            image.setCaseId(caseId);
            imageRepository.saveAndFlush(image);

            map.put("status", HttpStatus.OK.value());
            map.put("message", "Files uploaded successfully");
            status = HttpStatus.OK;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("@uploadFiles Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "uploadFiles", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(map, status);
    }


        }
