//package com.digiplan.controllers;
//
//import com.digiplan.entities.CaseUploadEntity;
//import com.digiplan.servicesImpl.CaseUploadServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@Slf4j
//@CrossOrigin(origins= "*")
//
//public class CaseUploadController {
//
//    @Autowired
//    private Environment env;
//    @Autowired
//    private CaseUploadServiceImpl service;
//
//    @PostMapping(value ={"/api/rest/saveCase"}, consumes = {"multipart/form-data"} )
//    public ResponseEntity<Map> saveData(@RequestParam(required = false) Long case_Id, @RequestParam String plan_No, @RequestParam String case_Type, @RequestParam String case_Category, @RequestParam(required = false) MultipartFile main_Img, @RequestParam(required = false) MultipartFile patient_properties, @RequestParam(required = false)  MultipartFile report_pdf,  @RequestParam(required = false)  MultipartFile upper_ipr,  @RequestParam(required = false)  MultipartFile lower_ipr,  @RequestParam(required = false)  MultipartFile front_video,  @RequestParam(required = false)  MultipartFile upper_video,  @RequestParam(required = false)  MultipartFile lower_video,  @RequestParam(required = false)  MultipartFile left_video,  @RequestParam(required = false)  MultipartFile right_video) {
//        return this.service.saveData(case_Id, plan_No, case_Type, case_Category,main_Img,patient_properties,report_pdf, upper_ipr, lower_ipr,front_video, upper_video, lower_video, left_video, right_video);
//    }
//
//    @PutMapping(value ={"/api/rest/updateCase/{id}"}, consumes = {"multipart/form-data"} )
//    public ResponseEntity<Map> updateData(@PathVariable Long id, @RequestParam(required = false) Long case_Id, @RequestParam String plan_No, @RequestParam String case_Type, @RequestParam String case_Category,   @RequestParam(required = false) MultipartFile main_Img,  @RequestParam(required = false)  MultipartFile patient_properties, @RequestParam(required = false)  MultipartFile report_pdf,  @RequestParam(required = false)  MultipartFile upper_ipr,  @RequestParam(required = false)  MultipartFile lower_ipr,  @RequestParam(required = false)  MultipartFile front_video,  @RequestParam(required = false)  MultipartFile upper_video, @RequestParam(required = false)  MultipartFile lower_video,  @RequestParam(required = false)  MultipartFile left_video,  @RequestParam(required = false)  MultipartFile right_video) {
//        return this.service.updateData(id,case_Id, plan_No, case_Type, case_Category, main_Img,patient_properties,report_pdf, upper_ipr, lower_ipr,front_video, upper_video, lower_video, left_video, right_video);//,main_Img,patient_properties,report_pdf, upper_ipr, lower_ipr,front_video, upper_video, lower_video, left_video, right_video
//    }
//    @GetMapping({"/api/rest/getCaseData/{id}"})
//    public ResponseEntity<Map> getId(@PathVariable Long id) {
//        Map<String, Object>  map = new HashMap<>();
//        HttpStatus status = null;
//        try{
//
//            CaseUploadEntity uploadEntity = service.getById(id);
//
//            if (uploadEntity!=null){
//                map.put("status_code", "200");
//                map.put("results", service.getById(id));
//                map.put("message","success");
//                status = HttpStatus.OK;
//            }
//            else{
//                map.put("status_code", "404");
//                map.put("result", "No content!");
//                map.put("message","No record found of id not exists");
//                status = HttpStatus.NOT_FOUND;
//            }
//        }catch (Exception e){
//            map.put("status_code", "500");
//            map.put("results", e.getMessage());
//            map.put("error","No record found of id not exists");
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(map,status);
//    }
//
//    @GetMapping({"/api/rest/getCaseIdData/{caseId}"})
//    public  ResponseEntity<Map> getCaseIdRecords(@PathVariable Long caseId){
//
//        List<CaseUploadEntity> entityList = this.service.getCaseIdRecords(caseId);
//        Map<String, Object> map = new HashMap<>();
//        HttpStatus status = null;
//        try{
//            if (!entityList.isEmpty()){
//                map.put("status", 200);
//                map.put("results", entityList);
//                map.put("message","Data found");
//                status = HttpStatus.OK;
//            }
//            else{
//                System.out.println("mai chala...");
//                map.put("status", 204);
//                map.put("message","No record found!");
//                status = HttpStatus.NO_CONTENT;
//            }
//
//        }catch (Exception ex){
//            map.put("status", 500);
//            map.put("results", ex.getMessage());
//            map.put("message","No record found of id not exists");
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            ex.printStackTrace();
//        }
//        return  new ResponseEntity<>(map,status);
//    }
//
//    @GetMapping({"/api/rest/getAlldata"})
//    public ResponseEntity<Map> getCaseRecords() {
//        List<CaseUploadEntity> entityList = this.service.getCaseRecords();
//        Map<String, Object>  map = new HashMap<>();
//        HttpStatus status = null;
//        try{
//
//            if (!entityList.isEmpty()){
//                map.put("status", 200);
//                map.put("results", entityList);
//                map.put("message","Data found");
//                status = HttpStatus.OK;
//            }
//            else{
//                map.put("status", 404);
//                map.put("message","No record found!");
//                status = HttpStatus.NOT_FOUND;
//            }
//        }catch (Exception e){
//            map.put("status", 500);
//            map.put("results", e.getMessage());
//            map.put("message","No record found of id not exists");
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(map,status);
//    }
//
//    @PutMapping({"/api/rest/delCaseStatus/{case_id}"})
//    public ResponseEntity<Map> saveCaseData(@RequestBody CaseUploadEntity uploadEntity){
//        Map<String,Object> map = new HashMap<>();
//        HttpStatus status = null;
//
//        try{
//            int cnt = service.updateDelStatus(uploadEntity);
//            if (cnt!=0){
//                map.put("status_code", 200);
//                map.put("results", uploadEntity);
//                map.put("message","Successfully Deleted");
//                status = HttpStatus.OK;
//            }
//            else {
//                map.put("status_code", 404);
//                map.put("message", "No content!");
//                status = HttpStatus.NOT_FOUND;
//            }
//        }catch (Exception ex){
//            map.put("status_code", 500);
//            map.put("error", ex.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            log.info(""+ ex.getMessage());
//        }
//        return new ResponseEntity<>(map,status);
//    }
//
//}
