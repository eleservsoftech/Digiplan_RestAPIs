package com.digiplan.servicesImpl;

import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.repositories.MidAssessmentRepository;
import com.digiplan.services.MidAssessmentService;
import com.digiplan.util.Utils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Log
public class MidAssessmentServiceImpl implements MidAssessmentService {

    @Autowired
    private MidAssessmentRepository midRepo;

    @Autowired
    private Utils utils;

    @Autowired
    private Environment env;

        @Override
        public ResponseEntity<Map> creaetMidScanReq(
                String caseId, String patientName, String doctorName,
                MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                String alignerNoU, String alignerNoL, String fittingOfAligner, String remarks, String user,
                String watts32UserRemarks, String watts32User,String folderName){
            Map<Object, Object> map = new HashMap<>();
            HttpStatus status = null;
            MidAssessmentEntity midAssessmentEntity = new MidAssessmentEntity();

            try {
                if (!caseId.isEmpty()){
                    folderName = caseId+"_"+(new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss")).format(new Date());
                    midAssessmentEntity.setCaseId(caseId);
                    midAssessmentEntity.setPatientName(patientName);
                    midAssessmentEntity.setDoctorName(doctorName);
                    midAssessmentEntity.setAlignerNoU(alignerNoU);
                    midAssessmentEntity.setAlignerNoL(alignerNoL);
                    midAssessmentEntity.setFittingOfAligner(fittingOfAligner);
                    midAssessmentEntity.setRemarks(remarks);
                    midAssessmentEntity.setUser(user);
                    midAssessmentEntity.setWatts32User(watts32User);
                    midAssessmentEntity.setWatts32UserRemarks(watts32UserRemarks);
                    midAssessmentEntity.setFolderName(folderName);
                    utils.uploadMidScanPhotos(folderName,photo1,photo2,photo3,photo4);
                    if(photo1!=null) {
                        midAssessmentEntity.setPhoto1(photo1.getOriginalFilename());
                    }if(photo2!=null)
                        midAssessmentEntity.setPhoto2(photo2.getOriginalFilename());
                    if(photo3!=null)
                        midAssessmentEntity.setPhoto3(photo3.getOriginalFilename());
                    if(photo4!=null)
                        midAssessmentEntity.setPhoto4(photo4.getOriginalFilename());
                    this.midRepo.saveAndFlush(midAssessmentEntity);
                    map.put("status_code", "200");
                    map.put("message", "Data saved successfully");
                    map.put("requestId", midAssessmentEntity.getRequestId());
                    status = HttpStatus.OK;
                }else{
                    map.put("status_code", "400");
                    map.put("message", "Case Id blank not accepted!");
                    status = HttpStatus.BAD_REQUEST;
                }
            } catch (Exception e) {
                map.put("status_code", "500");
                map.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                log.info("Exception @saveCaseBooking{} " + e.getMessage());
            }
            return new ResponseEntity(map, status);
        }
        // put
        @Override
        public ResponseEntity<Map<String, Object>> updateMidScanReq(
                Long requestId,
                String caseId, String patientName, String doctorName,
                MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                String alignerNoU, String alignerNoL, String fittingOfAligner, String remarks, String user,
                String watts32UserRemarks, String watts32User, String folderName) {
                Map<String, Object> response = new HashMap<>();
                HttpStatus status = null;

                try {
                Optional<MidAssessmentEntity> optionalEntity = midRepo.findById(requestId);
                if (optionalEntity.isPresent()) {
                    MidAssessmentEntity updateMidAssessment = optionalEntity.get();
                    MidAssessmentEntity checkData = midRepo.findByImage(String.valueOf(requestId));
                    folderName = checkData.getFolderName();
                    updateMidAssessment.setCaseId(caseId);
                    updateMidAssessment.setPatientName(patientName);
                    updateMidAssessment.setDoctorName(doctorName);
                    updateMidAssessment.setAlignerNoU(alignerNoU);
                    updateMidAssessment.setAlignerNoL(alignerNoL);
                    updateMidAssessment.setFittingOfAligner(fittingOfAligner);
                    updateMidAssessment.setRemarks(remarks);
                    updateMidAssessment.setUser(user);
                    updateMidAssessment.setWatts32User(watts32User);
                    updateMidAssessment.setWatts32UserRemarks(watts32UserRemarks);
                    if (folderName != null && !folderName.isEmpty()) {
                        updateMidAssessment.setFolderName(folderName);
                    }
                    // Upload photos if provided
                    utils.uploadMidScanPhotos(folderName,photo1,photo2,photo3,photo4);
                    if (photo1 != null) {
                        updateMidAssessment.setPhoto1(photo1.getOriginalFilename());
                    }
                    if (photo2 != null) {
                        updateMidAssessment.setPhoto2(photo2.getOriginalFilename());
                    }
                    if (photo3 != null) {
                        updateMidAssessment.setPhoto3(photo3.getOriginalFilename());
                    }
                    if (photo4 != null) {
                        updateMidAssessment.setPhoto4(photo4.getOriginalFilename());
                    }
                    midRepo.save(updateMidAssessment);

                    response.put("status_code", HttpStatus.OK.toString());
                    response.put("message", "Data Updated successfully");
                    status = HttpStatus.OK;
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request ID not found");
                }
            } catch (Exception e) {
                response.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
                response.put("message", e.getMessage());
                log.info("Exception @updateMidScanReq: " + e.getMessage());
            }

            return new ResponseEntity<>(response, status);
        }

   public ResponseEntity<Map<String, Object>> getMidAssessement(String requestId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
        try {
            Optional<MidAssessmentEntity> optionalEntity = midRepo.findById(Long.valueOf(requestId));

            //
            MidAssessmentEntity midEntity = midRepo.findByImage(requestId);
            String [] dynamicPhotoList = {midEntity.getPhoto1(), midEntity.getPhoto2(), midEntity.getPhoto3(), midEntity.getPhoto4() };

            //
            if (optionalEntity.isPresent()) {
                MidAssessmentEntity entity = optionalEntity.get();
                String folderName = entity.getFolderName();
                String folderPath = env.getProperty("file.midscan.location") + folderName;
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
                                imageData.put("byteArray", arr);
                                imageList.add(imageData);
                            }
                        }
                        map.put("status_code", HttpStatus.OK.toString());
                        map.put("message", "OK");
                        map.put("data", imageList);
                        status = HttpStatus.OK;
                    } else {
                        map.put("status_code", HttpStatus.NOT_FOUND.toString());
                        map.put("message", "No photos found in the folder");
                        status = HttpStatus.NOT_FOUND;
                    }
                } else {
                    map.put("status_code", HttpStatus.NOT_FOUND.toString());
                    map.put("message", "Folder is not present");
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "Request ID not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    // get all records
    public ResponseEntity<Map<String, Object>> getMidAssessements(MidAssessmentEntity midAssessmentEntity) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
        try {
            if(!this.midRepo.findAll().isEmpty()) {
                List<MidAssessmentEntity> midAssess = this.midRepo.findAll();
                String folderName = midAssessmentEntity.getFolderName();
                String fileName = midAssessmentEntity.getFolderName();

                //List<String> stringList = Arrays.asList(midAssessmentEntity.getPhoto1(), midAssessmentEntity.getPhoto2(), midAssessmentEntity.getPhoto3(),midAssessmentEntity.getPhoto4());


                String folderPath = env.getProperty("file.midscan.location") + folderName;
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
                                imageData.put("byteArray", arr);
                                imageList.add(imageData);
                            }
                        }
                        map.put("status_code", HttpStatus.OK.toString());
                        map.put("message", "OK");
                        map.put("data", imageList);
                        status = HttpStatus.OK;
                    } else {
                        map.put("status_code", HttpStatus.NOT_FOUND.toString());
                        map.put("message", "No photos found in the folder");
                        status = HttpStatus.NOT_FOUND;
                    }
                } else {
                    map.put("status_code", HttpStatus.NOT_FOUND.toString());
                    map.put("message", "Folder is not present");
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.toString());
                map.put("message", "Request ID not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    // mid all data
    @Override
    public ResponseEntity<Map> getMidAllData() {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (!this.midRepo.findAll().isEmpty()) {
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", this.midRepo.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", this.midRepo.findAll());
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log getMidAllData{0} " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }
}