package com.digiplan.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


    public interface AdditionalInfoCollectionService {
        ResponseEntity<Map<String, Object>> creaetAdditionalInfoCollection(
                String Id, String FormId, String folderName, String ImagePath, MultipartFile Image,
                String ImageType, String AdditionalInfoRemarks, String CreatedAt
        );

        ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionById(Long id);
    }


