package com.digiplan.controllers;

import com.digiplan.entities.Draft;
import com.digiplan.services.DraftService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class DraftController {

    @Autowired
    private DraftService draftService;

    @GetMapping("/getDraft/{id}")
    public Map<String,Object> getDraft(@PathVariable Integer id) {
        Map<String,Object> map=new HashMap<>();
        try {
            if (this.draftService.getDraft(id) != null) {
                map.put("status_code", "200");
                map.put("results", this.draftService.getDraft(id));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Draft Id is Invalid! ");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
            return map;
    }

    @GetMapping("/getAllDrafts")
    public Map<String,Object> getAllDrafts() {
        Map<String,Object> map=new HashMap<>();
        try {
            if (this.draftService.getAllDrafts() != null) {
                map.put("status_code", "200");
                map.put("results", this.draftService.getAllDrafts());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
        //return this.draftService.getAllDrafts();
    }

    @PostMapping("/savedraft")
    public ResponseEntity<Draft> addDraft(@RequestBody Draft draftData) {
        return new ResponseEntity<Draft>(this.draftService.addDraft(draftData), HttpStatus.CREATED);
    }

    // aman testing
//    @PostMapping("/savedraft")
//    public ResponseEntity<ApiResponse> addDraft(
//            @RequestBody Draft draftData,
//            @RequestParam("image1") MultipartFile image1,
//            @RequestParam("image2") MultipartFile image2,
//            @RequestParam("image3") MultipartFile image3,
//            @RequestParam("image4") MultipartFile image4,
//            @RequestParam("image5") MultipartFile image5,
//            @RequestParam("image6") MultipartFile image6,
    //        @RequestParam("image7") MultipartFile image7,
    //        @RequestParam("image8") MultipartFile image8,
//            @RequestParam("pdf1") MultipartFile pdf1) {
//
//        try {
//            Draft draft = draftService.addDraft(draftData, image1, image2, image3, image4, image5, image6, image7, image8,pdf1, draftData.getFile_path());
//
//            if (draft != null) {
//                ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), "Files uploaded successfully", true);
//                return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//            } else {
//                ApiResponse apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "File upload failed", false);
//                return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "File upload failed", false);
//            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    //

    @PutMapping("/updateDraft/{id}")
    public ResponseEntity<Draft> updateDraft(@PathVariable Integer id, @RequestBody Draft draftData) {
        Draft draft = this.draftService.updateDraft(id, draftData);
        if (draft != null)
            return new ResponseEntity<Draft>(draft, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@DeleteMapping("/deleteDraft/{id}")
    public ResponseEntity<String> deleteDraft(@PathVariable Integer id) {
        String status = this.draftService.deleteDraft(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @PostMapping("/viewdrafts")
    public ResponseEntity<Map> viewDrafts(@RequestBody Draft draftData) {
        return this.draftService.viewDrafts(draftData);
    }

    @DeleteMapping("/deleteDraft")
    public ResponseEntity<Map> deleteDraft(@RequestParam String draftId) {
        return this.draftService.deleteDraft(draftId);
    }
    
}
