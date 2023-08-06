package com.digiplan.services;

import com.digiplan.entities.Cases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CaseService {

    List<Cases> getAllCases();

    Cases addCase(Cases casesData);

    ResponseEntity<Map> myCases(String username);

    ResponseEntity<Object> downloadReport(String caseId);

    ResponseEntity<Map> getCaseDetails(String caseId);

    ResponseEntity<Map> uploadFiles(List<MultipartFile> file);

    ResponseEntity<Map> createCase(String formId, String caseId);

    ResponseEntity<Map> getVideos(String caseId, int planNo);
}
