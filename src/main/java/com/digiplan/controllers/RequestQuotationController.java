package com.digiplan.controllers;

import com.digiplan.entities.RequestQuotationEntity;
import com.digiplan.services.RequestQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class RequestQuotationController {

    private final RequestQuotationService requestQuotationService;
    private com.digiplan.services.RequestQuotationService RequestQuotationService;

    @Autowired
    public RequestQuotationController(RequestQuotationService requestQuotationService) {
        this.requestQuotationService = requestQuotationService;
    }

    @PostMapping(value = {"/addRequestQuotation"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Map> addRequestQuotation(
            @RequestParam String orthodontistName, @RequestParam String phone, @RequestParam String city,
            @RequestParam String submittedBy, @RequestParam String remarks, @RequestParam String patientName,
            @RequestParam String gender, @RequestParam String dob,
            @RequestParam(required = true) MultipartFile photo1,
            @RequestParam(required = false) MultipartFile photo2,
            @RequestParam(required = false) MultipartFile photo3,
            @RequestParam(required = false) MultipartFile photo4,
            @RequestParam(required = false) MultipartFile photo5,
            @RequestParam String flag, @RequestParam String doctorName, @RequestParam String treatmentCost,
            @RequestParam String duration, @RequestParam String crmStatus, @RequestParam String crmDecision,
            @RequestParam String crmBy, @RequestParam String crmDecisionAt
    ) {

        return requestQuotationService.creaetRequestQuotationReq(orthodontistName, phone, city, submittedBy, remarks,
                patientName, gender, dob, photo1, photo2, photo3, photo4, photo5, flag, doctorName, treatmentCost,
                duration, crmStatus, crmDecision, crmBy,
                LocalDateTime.parse(crmDecisionAt)

        );
    }

    @PutMapping(value = {"/updateRequestQuotation/{formId}"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Map> updateRequestQuotation(@PathVariable Long formId, @RequestParam String orthodontistName, @RequestParam String phone, @RequestParam String city,
                                                      @RequestParam String submittedBy, @RequestParam String remarks, @RequestParam String patientName,
                                                      @RequestParam String gender, @RequestParam String dob,
                                                      @RequestParam(required = true) MultipartFile photo1,
                                                      @RequestParam(required = false) MultipartFile photo2,
                                                      @RequestParam(required = false) MultipartFile photo3,
                                                      @RequestParam(required = false) MultipartFile photo4,
                                                      @RequestParam(required = false) MultipartFile photo5,
                                                      @RequestParam String flag, @RequestParam String doctorName, @RequestParam String treatmentCost,
                                                      @RequestParam String duration, @RequestParam String crmStatus, @RequestParam String crmDecision,
                                                      @RequestParam String crmBy, @RequestParam String crmDecisionAt, @RequestParam(required = false) String folderName) {

        return this.requestQuotationService.updateRequestQuotationReq(formId, orthodontistName, phone, city, submittedBy, remarks,
                patientName, gender, dob, photo1, photo2, photo3, photo4, photo5, flag, doctorName, treatmentCost,
                duration, crmStatus, crmDecision, crmBy,  LocalDateTime.parse(crmDecisionAt), folderName);
    }

    @GetMapping("/getRequestQuotation/{formId}")
    public ResponseEntity<Map<String, Object>> getRequestQuotation(@PathVariable String formId) {
        return requestQuotationService.getRequestQuotation(formId);
    }

    @GetMapping("/getRequestQuotation")
    public ResponseEntity<Map<String, Object>> getRequestQuotation(RequestQuotationEntity requestQuotation) {
        return requestQuotationService.getRequestQuotation(requestQuotation);
    }

    private boolean isImageFile(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg");
    }


    @GetMapping("/getRequestAllData")
    public  ResponseEntity<Map> getRequestQuotation()   {
        return this.requestQuotationService.getRequestQuotationAllData();
    }

}






