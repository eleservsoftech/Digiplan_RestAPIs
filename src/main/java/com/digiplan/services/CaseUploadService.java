package com.digiplan.services;


import com.digiplan.entities.CaseUploadEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CaseUploadService {

    ResponseEntity<Map> saveData(Long case_id, String plan_no, String case_type, String case_category, MultipartFile main_Img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video);

    ResponseEntity<Map> updateData(Long id,Long case_id, String plan_no, String case_type, String case_category,  MultipartFile main_img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video);

    CaseUploadEntity getById(Long id);
    List<CaseUploadEntity>  getCaseRecords();

    List<CaseUploadEntity> getCaseIdRecords(Long case_id);

    CaseUploadEntity getCase_Id(Long case_Id);

//    CaseUploadEntity updateDelStatus(CaseUploadEntity uploadEntity);

}