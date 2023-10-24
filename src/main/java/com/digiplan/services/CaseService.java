package com.digiplan.services;

import com.digiplan.entities.Cases;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CaseService {
    List<Cases> getAllCases();

   // Cases addCase(Cases paramCases);

    public ResponseEntity<Map> addCase(Cases casesData);

    ResponseEntity<Map> myCases(String paramString);


    ResponseEntity<Object> downloadReport(String paramString);

    ResponseEntity<Map> getCaseDetails(String paramString);

    ResponseEntity<Map> uploadFiles(List<MultipartFile> paramList);

    ResponseEntity<Map> createCase(String paramString1, String paramString2);

    ResponseEntity<Map> getVideos(String paramString, int paramInt);

   // ResponseEntity<Map> getUserData(String paramString);

    public  ResponseEntity<Map> getCaseId(String caseId);
    public ResponseEntity<Map> GetMyCaselist( String userName, String activeCases);

}
