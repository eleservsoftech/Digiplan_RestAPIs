package com.digiplan.controllers;

import com.digiplan.services.AdditionalInfoCollectionService;
import com.digiplan.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/additional-info-collection")
public class AdditionalInfoCollectionController {

    @Autowired
    private AdditionalInfoCollectionService additionalInfoCollectionService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Utils utils;

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, Object>> createAdditionalInfoCollection(
            @RequestParam String Id,
            @RequestParam String formId,
            @RequestParam String folderName,
            @RequestParam String imagePath,
            @RequestParam MultipartFile image,
            @RequestParam String imageType,
            @RequestParam String additionalInfoRemarks,
            @RequestParam String createdAt
    ) {
        return this.additionalInfoCollectionService.creaetAdditionalInfoCollection(
                Id, formId, folderName, imagePath, image, imageType, additionalInfoRemarks, createdAt);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionById(@PathVariable Long id) {
        return additionalInfoCollectionService.getAdditionalInfoCollectionById(id);
    }
}
