package com.digiplan.services;

import com.digiplan.entities.MidAssessmentEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MidAssessmentService {
    public ResponseEntity<Map> creaetMidScanReq(
            String caseId, String patientName, String doctorName,
            MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
            MultipartFile photo5, MultipartFile photo6, MultipartFile photo7, MultipartFile photo8,MultipartFile photo9,MultipartFile photo10,
            String alignerNoU, String alignerNoL, String fittingOfAligner, String remarks, String user,
            String watts32UserRemarks, String watts32User,String folderName);//,String filePath

    public ResponseEntity<Map<String, Object>> updateMidScanReq(
            Long requestId,
            String caseId, String patientName, String doctorName,
            MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
            MultipartFile photo5, MultipartFile photo6, MultipartFile photo7, MultipartFile photo8,MultipartFile photo9,MultipartFile photo10,
            String alignerNoU, String alignerNoL, String fittingOfAligner, String remarks, String user,
            String watts32UserRemarks, String watts32User, String folderName) ;
    public ResponseEntity<Map<String, Object>> getMidAssessement(String requestId);
    public ResponseEntity<Map<String, Object>> getMidAssessements(MidAssessmentEntity midAssessmentEntity);
    public ResponseEntity<Map> getMidAllData();

}
