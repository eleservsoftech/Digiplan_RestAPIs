package com.digiplan.servicesImpl;

import com.digiplan.entities.AdditionalInfoCollection;
import com.digiplan.repositories.AdditionalInfoCollectionRepository;
import com.digiplan.services.AdditionalInfoCollectionService;
import com.digiplan.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class AdditionalInfoCollectionServiceImpl implements AdditionalInfoCollectionService {

    @Autowired
    private AdditionalInfoCollectionRepository additionalInfoCollectionRepo;

    @Autowired
    private Utils utils;

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<Map<String, Object>> creaetAdditionalInfoCollection(String FormId, MultipartFile Image,
                                                                              String AdditionalInfoRemarks
    ) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        AdditionalInfoCollection additionalInfoCollection = new AdditionalInfoCollection();
        String folderName = "";
        try {
            if (!FormId.isEmpty()) {
                folderName = FormId + "_" + (new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")).format(new Date());
                additionalInfoCollection.setFormId(Long.parseLong(FormId));


                additionalInfoCollection.setAdditionalInfoRemarks(AdditionalInfoRemarks);

                additionalInfoCollection.setFolderName(folderName);
                if (Image != null) {

                    String fileName = utils.uploadAdditionalInfoPhotos(folderName, Image);
                    additionalInfoCollection.setImagePath(env.getProperty("file.additionalinfo.location") + folderName);
                    additionalInfoCollection.setImage(fileName);
                    this.additionalInfoCollectionRepo.saveAndFlush(additionalInfoCollection);
                    map.put("folderName",folderName);
                    map.put("image",Image.getOriginalFilename());
                    map.put("status_code", "200");
                    map.put("message", "Data saved successfully");
                    map.put("requestId", additionalInfoCollection.getId());
                    status = HttpStatus.OK;
                } else {
                    map.put("status_code", "400");
                    map.put("message", "Case Id blank not accepted!");
                    status = HttpStatus.BAD_REQUEST;
                }
            } else {
                map.put("status_code", "400");
                map.put("message", "Id is empty!");
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


    @Override
    public ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionById(Long id) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            Optional<AdditionalInfoCollection> additionalInfoCollectionOptional = additionalInfoCollectionRepo.findById(id);
            if (additionalInfoCollectionOptional.isPresent()) {
                AdditionalInfoCollection additionalInfoCollection = additionalInfoCollectionOptional.get();

                // Convert image file to byte array
                File imageFile = new File(additionalInfoCollection.getImagePath() + File.separator + additionalInfoCollection.getImage());
                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

                // Add additional information to the response map
                map.put("data", additionalInfoCollection);
                map.put("image_bytes", imageBytes);
                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");

                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("message", "Additional info collection with ID " + id + " not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception @getAdditionalInfoCollectionById: " + e.getMessage());
        }

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionByFormId(Long formId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
            List<AdditionalInfoCollection> additionalInfoCollections = additionalInfoCollectionRepo.findByFormId(formId);
            if (!additionalInfoCollections.isEmpty()) {
                List<Map<String, Object>> additionalInfoList = new ArrayList<>();
                for (AdditionalInfoCollection additionalInfoCollection : additionalInfoCollections) {
                    Map<String, Object> additionalInfoMap = new HashMap<>();
                    additionalInfoMap.put("additional_info", additionalInfoCollection);

                    // Convert image file to byte array
                    File imageFile = new File(additionalInfoCollection.getImagePath() + File.separator + additionalInfoCollection.getImage());
                    byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
                    additionalInfoMap.put("image_bytes", imageBytes);

                    additionalInfoList.add(additionalInfoMap);
                }

                map.put("data", additionalInfoList);
                map.put("status_code", HttpStatus.OK.toString());
                map.put("message", "OK");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("message", "No additional info collections found for form ID " + formId);
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Exception @getAdditionalInfoCollectionByFormId: " + e.getMessage());
        }

        return new ResponseEntity<>(map, status);
    }



}
