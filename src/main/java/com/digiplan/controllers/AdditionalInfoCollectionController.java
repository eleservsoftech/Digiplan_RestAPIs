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

            @RequestParam String formId,
            @RequestParam MultipartFile image,
            @RequestParam String additionalInfoRemarks

    ) {
        return this.additionalInfoCollectionService.creaetAdditionalInfoCollection(
               formId,   image, additionalInfoRemarks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionById(@PathVariable Long id) {
        return additionalInfoCollectionService.getAdditionalInfoCollectionById(id);
    }

    @GetMapping("/by-form/{formId}")
    public ResponseEntity<Map<String, Object>> getAdditionalInfoCollectionByFormId(@PathVariable Long formId) {
        return additionalInfoCollectionService.getAdditionalInfoCollectionByFormId(formId);
    }

}
