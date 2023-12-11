package com.digiplan.servicesImpl;

import com.digiplan.entities.Gallery;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.GalleryRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.GalleryService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Gallery getGalleryData(String caseId) {
        Gallery gallery = null;
        try {
            Optional<Gallery> check = galleryRepository.findById(caseId);
            if (check.isPresent())
                gallery = galleryRepository.getById(caseId);
        } catch (Exception exception) {
            System.out.println("@getGalleryData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getGalleryData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return gallery;
    }

    @Override
    public List<Gallery> getAllGalleryData() {
        List<Gallery> galleryList = null;
        try {
            galleryList = galleryRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllGalleryData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllGalleryData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return galleryList;
    }

    @Override
    public Gallery addGalleryData(Gallery galleryData) {
        Gallery gallery = null;
        try {
            gallery = galleryRepository.saveAndFlush(galleryData);
        } catch (Exception exception) {
            System.out.println("@addGalleryData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addGalleryData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return gallery;
    }

    @Override
    public Gallery updateGalleryData(String caseId, Gallery galleryData) {
        Gallery gallery = null;
        try {
            Optional<Gallery> check = galleryRepository.findById(caseId);
            if (check.isPresent())
                gallery = galleryRepository.saveAndFlush(galleryData);
        } catch (Exception exception) {
            System.out.println("@updateGalleryData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateGalleryData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return gallery;
    }

    @Override
    public String deleteGalleryData(String caseId) {
        String status = "";
        try {
            galleryRepository.deleteById(caseId);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteGalleryData Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteGalleryData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

    @Override
    public ResponseEntity<Map> getSamples() {
        JSONParser jsonParser = new JSONParser();
        List list = new ArrayList();
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<Gallery> galleryList = galleryRepository.findAll();
            if (galleryList == null) {
                map.put("status", "404");
                map.put("message", "No Data Found");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                for (Gallery gallery : galleryList) {
                    JSONObject jsonObject = new JSONObject();
                    JSONObject extractedData = (JSONObject) jsonParser.parse(gallery.getFormData());
                    jsonObject.put("patientName", extractedData.get("PatientName"));
                    jsonObject.put("serialNumber", extractedData.get("serialnumber"));
                    jsonObject.put("dob", extractedData.get("DOB"));
                    jsonObject.put("date", extractedData.get("date"));
                    jsonObject.put("data", extractedData);
                    list.add(jsonObject);
                }
                map.put("status", "200");
                map.put("message", "Data Found");
                map.put("data", list);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@getSamples Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getSamples", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    public ResponseEntity<Map> getSampleFilterData(String caseType, String caseCategory, String gender) {
        JSONParser jsonParser = new JSONParser();
        List list = new ArrayList();
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<Gallery> galleryList = galleryRepository.getSampleDataList(caseType, caseCategory, gender);
            int count = 1;
            for (Gallery gallery : galleryList) {
                JSONObject jsonObject = new JSONObject();
                JSONObject extractedData = (JSONObject) jsonParser.parse(gallery.getFormData());
                jsonObject.put(
                        "sno", count++);
                jsonObject.put("patientName", extractedData.get("PatientName"));
                jsonObject.put("caseId", gallery.getCaseId());
                jsonObject.put("dob", extractedData.get("DOB"));
                jsonObject.put("gender", gallery.getGender());
                jsonObject.put("caseDate", extractedData.get("CaseDate"));
                jsonObject.put("caseType", gallery.getCaseType());
                jsonObject.put("caseCategory", gallery.getCaseCategory());
                list.add(jsonObject);
            }
            if (list.isEmpty()) {
                map.put("status", "404");
                map.put("message", "No Data Found");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", "200");
                map.put("message", "Data Found");
                map.put("data", list);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@getSampleFilterData Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getSampleFilterData", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

}
