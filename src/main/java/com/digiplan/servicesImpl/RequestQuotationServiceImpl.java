package com.digiplan.servicesImpl;


import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.entities.RequestQuotationEntity;
import com.digiplan.repositories.RequestQuotationRepository;
import com.digiplan.services.RequestQuotationService;
import com.digiplan.util.Utils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Log
public class RequestQuotationServiceImpl implements RequestQuotationService {

    @Autowired
    public RequestQuotationRepository requestRepo;

    @Autowired
    private Utils utils;

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<Map> creaetRequestQuotationReq(String orthodonstistName, String phone, String City,
                                                         String submittedby, String remarks, String patientname, String gender,
                                                         String dob,
                                                         MultipartFile photo1, MultipartFile photo2, MultipartFile photo3,
                                                         MultipartFile photo4, MultipartFile photo5,
                                                         String flag, String doctorName, String treatmentCost, String duration,
                                                         String crmStatus, String crmDecision,
                                                         String crmBy
    ) {

        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        RequestQuotationEntity requestQuotationEntity = new RequestQuotationEntity();

        try {
            if (!phone.isEmpty()) {
                String folderName = phone + "_" + (new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")).format(new Date());
                requestQuotationEntity.setSubmittedOn(LocalDate.now());
                requestQuotationEntity.setOrthodonstistName(orthodonstistName);
                requestQuotationEntity.setPhone(phone);
                requestQuotationEntity.setCity(City);
                requestQuotationEntity.setSubmittedby(submittedby);
                requestQuotationEntity.setRemarks(remarks);
                requestQuotationEntity.setDob(LocalDate.parse(dob));
                requestQuotationEntity.setPatientname(patientname);
                requestQuotationEntity.setGender(gender);
                requestQuotationEntity.setCrmDecesionAt(new Date());
                requestQuotationEntity.setFolderName(folderName);
                utils.uploadRequestQuotationPhotos(folderName, photo1, photo2, photo3, photo4, photo5);

                if (photo1 != null) {
                    requestQuotationEntity.setPhoto1(photo1.getOriginalFilename());
                }
                if (photo2 != null) {
                    requestQuotationEntity.setPhoto2(photo2.getOriginalFilename());
                }
                if (photo3 != null) {
                    requestQuotationEntity.setPhoto3(photo3.getOriginalFilename());
                }
                if (photo4 != null) {
                    requestQuotationEntity.setPhoto4(photo4.getOriginalFilename());
                }
                if (photo5 != null) {
                    requestQuotationEntity.setPhoto5(photo5.getOriginalFilename());
                }

                requestQuotationEntity.setFlag(flag);
                requestQuotationEntity.setDoctorName(doctorName);
                requestQuotationEntity.setTreatmentCost(treatmentCost);
                requestQuotationEntity.setDuration(duration);
                requestQuotationEntity.setCrmStatus(crmStatus);
                requestQuotationEntity.setCrmDecision(crmDecision);
                requestQuotationEntity.setCrmBy(crmBy);
                requestQuotationEntity.setCrmDecesionAt(new Date());
                if(treatmentCost!=null)
                    requestQuotationEntity.setTreatmentCost("0");
                else requestQuotationEntity.setTreatmentCost(treatmentCost);

                this.requestRepo.saveAndFlush(requestQuotationEntity);


                map.put("status_code", "200");
                map.put("message", "Data saved successfully");
                map.put("requestId", requestQuotationEntity.getFormId());
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "400");
                map.put("message", "Phone number cannot be empty!");
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Exception @creaetRequestQuotationReq: " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }



    @Override
    public ResponseEntity<Map> updateRequestQuotationReq(Long formId, String orthodonstistName, String phone, String City,
                                                         String submittedby, String remarks, String patientname, String gender, String dob,
                                                         MultipartFile photo1, MultipartFile photo2,
                                                         MultipartFile photo3, MultipartFile photo4, MultipartFile photo5,
                                                         String flag, String doctorName, String treatmentCost, String duration,
                                                         String crmStatus, String crmDecision, String crmBy, String folderName) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<RequestQuotationEntity> optionalEntity = requestRepo.findById(formId);

            if (optionalEntity.isPresent()) {
                RequestQuotationEntity updateRequestQuotation = optionalEntity.get();
                RequestQuotationEntity checkData = requestRepo.findByImage(String.valueOf(formId));
                folderName = checkData.getFolderName();

                updateRequestQuotation.setPhone(phone);
                updateRequestQuotation.setCity(City);
                updateRequestQuotation.setSubmittedby(submittedby);
                updateRequestQuotation.setRemarks(remarks);
                updateRequestQuotation.setDob(LocalDate.parse(dob));
                updateRequestQuotation.setPatientname(patientname);
                updateRequestQuotation.setGender(gender);
                updateRequestQuotation.setCrmDecesionAt(new Date());
                if(treatmentCost!=null)
                    updateRequestQuotation.setTreatmentCost("0");
                else updateRequestQuotation.setTreatmentCost(treatmentCost);
                if (folderName != null && !folderName.isEmpty()) {
                    updateRequestQuotation.setFolderName(folderName);
                }

                utils.uploadRequestQuotationPhotos(folderName, photo1, photo2, photo3, photo4, photo5);

                if (photo1 != null) {
                    updateRequestQuotation.setPhoto1(photo1.getOriginalFilename());
                }
                if (photo2 != null) {
                    updateRequestQuotation.setPhoto2(photo2.getOriginalFilename());
                }
                if (photo3 != null) {
                    updateRequestQuotation.setPhoto3(photo3.getOriginalFilename());
                }
                if (photo4 != null) {
                    updateRequestQuotation.setPhoto4(photo4.getOriginalFilename());
                }
                if (photo5 != null) {
                    updateRequestQuotation.setPhoto5(photo5.getOriginalFilename());
                }

                updateRequestQuotation.setFlag(flag);
                updateRequestQuotation.setDoctorName(doctorName);
                updateRequestQuotation.setTreatmentCost(treatmentCost);
                updateRequestQuotation.setDuration(duration);
                updateRequestQuotation.setCrmStatus(crmStatus);
                updateRequestQuotation.setCrmDecision(crmDecision);
                updateRequestQuotation.setCrmBy(crmBy);
                updateRequestQuotation.setCrmDecesionAt(new Date());

                requestRepo.save(updateRequestQuotation);
                response.put("status_code", HttpStatus.OK.toString());
                response.put("message", "Data Updated successfully");
                status = HttpStatus.OK;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request ID not found");
            }
        } catch (Exception e) {
            response.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.put("message", e.getMessage());
            log.info("Exception @updateRequestQuotationReq: " + e.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getRequestQuotation(String formId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
        try {
            Optional<RequestQuotationEntity> optionalEntity = requestRepo.findById(Long.valueOf(formId));

            //
            RequestQuotationEntity requestEntity = requestRepo.findByImage(formId);
            String[] dynamicPhotoList = {requestEntity.getPhoto1(), requestEntity.getPhoto2(), requestEntity.getPhoto3(), requestEntity.getPhoto4(),
                    requestEntity.getPhoto5()};

            //
            if (optionalEntity.isPresent()) {
                RequestQuotationEntity entity = optionalEntity.get();
                String folderName = entity.getFolderName();
                String folderPath = env.getProperty("file.requestquotation.location") + folderName;
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
                        map.put("otherData",requestEntity);
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
                map.put("message", "Form Id not found");
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
    public ResponseEntity<Map<String, Object>> getRequestQuotation(RequestQuotationEntity requestQuotationEntity) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status
        try {
            if (!this.requestRepo.findAll().isEmpty()) {
                List<RequestQuotationEntity> requestQuotation = this.requestRepo.findAll();
                String folderName = requestQuotationEntity.getFolderName();
                String fileName = requestQuotationEntity.getFolderName();

                String folderPath = env.getProperty("file.requestquotation.location") + folderName;
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
                map.put("message", "Form ID not found");
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
    public ResponseEntity<Map> getRequestQuotationAllData(){
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (!this.requestRepo.findAll().isEmpty()) {
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", this.requestRepo.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", this.requestRepo.findAll());
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

