package com.digiplan.servicesImpl;

import com.digiplan.entities.CaseUploadEntity;
import com.digiplan.repositories.CaseUploadReopsitory;
import com.digiplan.services.CaseUploadService;
import com.digiplan.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CaseUploadServiceImpl implements CaseUploadService {

    @Autowired
    private CaseUploadReopsitory repository;

    @Autowired
    private CaseUploadEntity entity;

    @Autowired
    private Environment environment;
    @Autowired
    private Utils utils;

    //add image data
    @Override
    public ResponseEntity<Map> saveData(Long case_id, String plan_no, String case_type, String case_category, MultipartFile main_Img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video){
        Map<Object,Object> map = new HashMap<>();
        HttpStatus status = null;
        CaseUploadEntity entity = new CaseUploadEntity();

        try {

            String caseIdPlan = "";
            CaseUploadEntity existData = repository.isPlanPresent(case_id,plan_no);
            log.info("existData: "+existData);
            if (existData==null){
                if ( plan_no!=null && !plan_no.equals("") ) {
                    System.out.println("if plan_no: "+plan_no);
                    caseIdPlan = String.valueOf(Long.valueOf(String.valueOf(case_id)) + "-" + plan_no);
                    log.info("if caseIdPlan : "+caseIdPlan);
                }else {
                    //caseIdPlan = String.valueOf(Long.valueOf(String.valueOf(case_id)) + "-" + plan_no);
                    caseIdPlan = String.valueOf(case_id);
                    log.info("else caseIdPlan : "+caseIdPlan);
                }
                utils.Directires(caseIdPlan, main_Img, patient_properties, report_pdf,upper_ipr, lower_ipr,front_video, upper_video, lower_video, left_video, right_video);
                entity.setCase_Id(case_id);
                entity.setPlan_No(plan_no);
                entity.setCase_Type(case_type);
                entity.setCase_Category(case_category);
                if(!main_Img.isEmpty() && main_Img!=null)
                    entity.setMain_Img(main_Img.getOriginalFilename());
                if(!patient_properties.isEmpty() && patient_properties!=null)
                    entity.setPatient_properties(patient_properties.getOriginalFilename());
                if(!report_pdf.isEmpty() && report_pdf!=null)
                    entity.setReport_pdf(report_pdf.getOriginalFilename());
                if(!upper_ipr.isEmpty() && upper_ipr!=null)
                    entity.setUpper_ipr(upper_ipr.getOriginalFilename());
                if(!lower_ipr.isEmpty() && lower_ipr!=null)
                    entity.setLower_ipr(lower_ipr.getOriginalFilename());
                if(!front_video.isEmpty() && front_video!=null)
                    entity.setFront_video(front_video.getOriginalFilename());
                if(!upper_video.isEmpty() && upper_video!=null)
                    entity.setUpper_video(upper_video.getOriginalFilename());
                if(!lower_video.isEmpty() && lower_video!=null)
                    entity.setLower_video(lower_video.getOriginalFilename());
                if(!left_video.isEmpty() && left_video!=null)
                    entity.setLeft_video(left_video.getOriginalFilename());
                if(!right_video.isEmpty() && right_video!=null)
                    entity.setRight_video(right_video.getOriginalFilename());
                //entity.setIs_deleted(is_deleted);
                this.repository.saveAndFlush(entity);
                map.put("status_code", "200");
                map.put("message", "Data saved successfully");
                status = HttpStatus.OK;
            }else{
                map.put("status_code", "403");
                map.put("message", "CaseId with this plan is already present");
                status = HttpStatus.ALREADY_REPORTED;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  saveData{}");
            e.printStackTrace();
        }
        return new ResponseEntity(map, status);
    }

    @Override
    //public ResponseEntity<Map> updateData(Long id , Long case_id, String plan_no, String case_type, String case_category,  MultipartFile main_img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video) {//, MultipartFile main_img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video
    public ResponseEntity<Map> updateData(Long id , Long case_id, String plan_no, String case_type, String case_category,  MultipartFile main_img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video) {//, MultipartFile main_img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video

        log.info("updateData case_type==== "+case_type+"case_category: "+case_category);

        Map<Object,Object> map = new HashMap<>();
        HttpStatus status = null;

        try{
            if (repository.findById(id).isPresent()) {
                CaseUploadEntity existtingdata = repository.findById(id).get();
                System.out.println("IMPL main_img:====> "+main_img+" upper_ipr: "+upper_ipr+" front_video: "+front_video);

                log.info("case_id: "+case_id+" plan_no: "+plan_no+" case_type: "+case_type+" case_category: "+case_category);
                this.utils.Directires1(String.valueOf(case_id), main_img, patient_properties, report_pdf, upper_ipr, lower_ipr, front_video, upper_video, lower_video, left_video, right_video);
                existtingdata.setCase_Id(case_id);
                existtingdata.setPlan_No(plan_no);
                existtingdata.setCase_Type(case_type);
                existtingdata.setCase_Category(case_category);
                if(!main_img.isEmpty() && main_img!=null)
                    existtingdata.setMain_Img(main_img.getOriginalFilename());
                if(!patient_properties.isEmpty() && patient_properties!=null)
                    existtingdata.setPatient_properties(patient_properties.getOriginalFilename());
                if(!report_pdf.isEmpty() && report_pdf!=null)
                    existtingdata.setReport_pdf(report_pdf.getOriginalFilename());
                if(!upper_ipr.isEmpty() && upper_ipr!=null)
                    existtingdata.setUpper_ipr(upper_ipr.getOriginalFilename());
                if(!lower_ipr.isEmpty() && lower_ipr!=null)
                    existtingdata.setLower_ipr(lower_ipr.getOriginalFilename());
                if(!front_video.isEmpty() && front_video!=null)
                    existtingdata.setFront_video(front_video.getOriginalFilename());
                if(!upper_video.isEmpty() && upper_video!=null)
                    existtingdata.setUpper_video(upper_video.getOriginalFilename());
                if(!lower_video.isEmpty() && lower_video!=null)
                    existtingdata.setLower_video(lower_video.getOriginalFilename());
                if(!left_video.isEmpty() && left_video!=null)
                    existtingdata.setLeft_video(left_video.getOriginalFilename());
                if(!right_video.isEmpty() && right_video!=null)
                    existtingdata.setRight_video(right_video.getOriginalFilename());
                this.repository.saveAndFlush(existtingdata);
                log.info("update: "+existtingdata.toString());
                map.put("status_code", "200");
                map.put("message", "Data updated successfully");
                status = HttpStatus.OK;
            }
            else {
                map.put("status_code","404");
                map.put("message", "Data not updates!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  updatePatientData{}"+e.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    @Override
    public   CaseUploadEntity  getById(Long id){

        CaseUploadEntity uploadEntity = null;
        try {
            uploadEntity = repository.findById(id).get();
        }catch (Exception ex){
            System.out.println("impl");
            ex.printStackTrace();
        }
        return uploadEntity;
    }

    @Override
    public List<CaseUploadEntity> getCaseRecords(){

        List<CaseUploadEntity> result = null;
        try {
            result = repository.getCaseRecords();
        }catch (Exception ex){
            System.out.println("impl");
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public  List<CaseUploadEntity>  getCaseIdRecords(Long case_id){
        List<CaseUploadEntity> result = null;
        try {
            result = repository.getCaseIdRecords(case_id);
            System.out.println("getCaseIdRecords:" +result);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public CaseUploadEntity getCase_Id(Long case_Id) {
        CaseUploadEntity uploadEntity = null;
        try {
            uploadEntity = repository.findById(case_Id).get();
            System.out.println("case idImpl service: "+uploadEntity);
        }catch (Exception ex){
            System.out.println("impl");
            ex.printStackTrace();
        }
        return uploadEntity;
    }

    ////update status

    public int  updateDelStatus(CaseUploadEntity uploadEntity){
        int  cnt = 0;
        try{
            cnt = repository.caseStatusUpdate(uploadEntity.getCase_Id(),uploadEntity.getPlan_No());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return cnt;
    }
}
