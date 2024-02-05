package com.digiplan.services;

import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.entities.RequestQuotationEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

public interface RequestQuotationService {

        public ResponseEntity<Map> creaetRequestQuotationReq(
                String orthodonstistName, String phone,
                String City, String submittedby, String remarks,
                String patientname, String gender, String dob,
                MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                MultipartFile photo5,
                String flag, String doctorName, String treatmentCost, String duration,
                String crmStatus, String crmDecision, String crmBy, LocalDateTime crmDecesionAt);

        public ResponseEntity<Map> updateRequestQuotationReq(
                Long formId,
                String orthodonstistName, String phone,
                String City, String submittedby, String remarks,
                String patientname, String gender, String dob,
                MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                MultipartFile photo5,
                String flag, String doctorName, String treatmentCost, String duration,
                String crmStatus, String crmDecision, String crmBy, LocalDateTime crmDecesionAt,String folderName);

        public ResponseEntity<Map<String, Object>> getRequestQuotation(String formId);

        public ResponseEntity<Map<String, Object>> getRequestQuotation(RequestQuotationEntity RequestQuotationEntity);

        public ResponseEntity<Map> getRequestQuotationAllData();



}