package com.digiplan.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


    public interface AdditionalInfoCollectionService {
        ResponseEntity<Map<String, Object>> creaetAdditionalInfoCollection(
                String FormId,   MultipartFile Image,
                 String AdditionalInfoRemarks
        );

        ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionById(Long id);
    }


