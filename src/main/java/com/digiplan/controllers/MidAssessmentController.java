package com.digiplan.controllers;

import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.repositories.MidAssessmentRepository;
import com.digiplan.services.MidAssessmentService;
import com.digiplan.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController

public class MidAssessmentController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MidAssessmentService midAssessmentService;

    @Autowired
    private Utils utils;


    @PostMapping(value = {"/addMidScanReg"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Map> addMidScanReg(String caseId, String patientName, String doctorName,
                                                  @RequestParam(required = true) MultipartFile photo1 , @RequestParam(required = false) MultipartFile photo2 ,
                                                  @RequestParam(required = false) MultipartFile photo3 , @RequestParam(required = false) MultipartFile photo4
                                                  ,@RequestParam(required = false) String alignerNoU, @RequestParam(required = false) String alignerNoL,
                                                  @RequestParam(required = false) String fittingOfAligner, @RequestParam(required = false) String remarks,
                                                  @RequestParam(required = false) String user,
                                                  @RequestParam(required = false) String watts32UserRemarks, @RequestParam(required = false) String watts32User, @RequestParam(required = false) String folderName) //,@RequestParam(required = false)  String filePath
    {
        return this.midAssessmentService.creaetMidScanReq(caseId, patientName, doctorName, photo1, photo2,
                photo3, photo4, alignerNoU, alignerNoL, fittingOfAligner, remarks, user, watts32UserRemarks,
                watts32User,folderName);//,filePath
    }

<<<<<<< HEAD
    @PutMapping(value = {"/updateMidScan/{requestId}"}, consumes = {"multipart/form-data"})
=======
    @PostMapping(value = {"/updateMidScan/{requestId}"}, consumes = {"multipart/form-data"})
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
    public ResponseEntity<Map<String, Object>> updateMidScanReg(Long requestId , String caseId, String patientName, String doctorName,
                                                                @RequestParam(required = true) MultipartFile photo1 , @RequestParam(required = false) MultipartFile photo2 ,
                                                                @RequestParam(required = false) MultipartFile photo3 , @RequestParam(required = false) MultipartFile photo4
            , @RequestParam(required = false) String alignerNoU, @RequestParam(required = false) String alignerNoL,
                                                                @RequestParam(required = false) String fittingOfAligner, @RequestParam(required = false) String remarks,
                                                                @RequestParam(required = false) String user,
                                                                @RequestParam(required = false) String watts32UserRemarks, @RequestParam(required = false) String watts32User, @RequestParam(required = false) String folderName) //,@RequestParam(required = false)  String filePath
    {
        return this.midAssessmentService.updateMidScanReq(requestId,caseId, patientName, doctorName, photo1, photo2,
                photo3, photo4, alignerNoU, alignerNoL, fittingOfAligner, remarks, user, watts32UserRemarks,
                watts32User,folderName);
    }

    @Autowired
    private MidAssessmentRepository midRepo;
<<<<<<< HEAD

    @GetMapping("/getMidScan/{requestId}")
    public ResponseEntity<Map<String, Object>> getMidCaseScan(@PathVariable String requestId) {
        return midAssessmentService.getMidAssessement(requestId);
    }

    @GetMapping("/getMidScans")
    public ResponseEntity<Map<String, Object>> getMidCaseScans(MidAssessmentEntity midAssessment) {
        return midAssessmentService.getMidAssessements( midAssessment);
    }



//    @GetMapping("/list")
//    public ResponseEntity<List<String>> getImageList() {
//        // Specify the directory path where your images are stored
//        String directoryPath = "D:/usr/digiplan/midscan/1234444_23.09.2023.20.07.13";
//        File directory = new File(directoryPath);
//        List<String> imageFileNames = new ArrayList<>();
//
//        if (directory.isDirectory()) {
//            File[] files = directory.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && isImageFile(file.getName())) {
//                        imageFileNames.add(file.getName());
//                    }
//                }
//            }
//        }
//
//        // Check if any images were found
//        if (imageFileNames.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(imageFileNames);
//        }
//    }

=======
// real
//    @GetMapping("/getMidScan/{requestId}")
//    public ResponseEntity<Map<String, Object>> getMidCaseScan(@PathVariable String requestId) {
//        return midAssessmentService.getMidAssessement(requestId);
//    }
// real

    @GetMapping("/getMidScan/{requestId}")
    public ResponseEntity<?> getMidCaseScan(@PathVariable Long requestId) {
        return midAssessmentService.getMidAssessment(requestId);

    }
//    @GetMapping("/getMidScans")
//    public ResponseEntity<Map<String, Object>> getMidCaseScans(MidAssessmentEntity midAssessment) {
//        return midAssessmentService.getMidAssessements( midAssessment);
//    }


    @GetMapping("/getAllMidScan")
    public ResponseEntity<Map<String, Object>> getAllMidAssessments() {
        return midAssessmentService.getAllMidAssessments();
    }



    @GetMapping("/list")
    public ResponseEntity<List<String>> getImageList() {
        // Specify the directory path where your images are stored
        String directoryPath = "D:/usr/digiplan/midscan/1234444_23.09.2023.20.07.13";
        File directory = new File(directoryPath);
        List<String> imageFileNames = new ArrayList<>();

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file.getName())) {
                        imageFileNames.add(file.getName());
                    }
                }
            }
        }

        // Check if any images were found
        if (imageFileNames.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(imageFileNames);
        }
    }

>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
    private boolean isImageFile(String fileName) {
        // Add logic to check if a file is an image based on its extension
        // You can customize this logic based on your file naming conventions
        return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg");
    }


    @GetMapping("/getMidAllData")
    public  ResponseEntity<Map> getAlignerWearingSchedules()   {
        return this.midAssessmentService.getMidAllData();
    }

}