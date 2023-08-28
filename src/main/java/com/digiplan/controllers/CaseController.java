package com.digiplan.controllers;

import com.digiplan.entities.Cases;
import com.digiplan.entities.User;
import com.digiplan.services.CaseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"*"})
@RestController
public class CaseController {
    @Autowired
    private CaseService caseService;

    @GetMapping({"/getAllCases"})
    public Map<String, Object> getAllCases() {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.caseService.getAllCases().size() > 0) {
                map.put("status_code", "200");
                map.put("results", this.caseService.getAllCases());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;
    }

    @PostMapping({"/savecase"})
    public ResponseEntity<Cases> addCase(@RequestBody Cases casesData) {
        return new ResponseEntity(this.caseService.addCase(casesData), HttpStatus.CREATED);
    }

    @PostMapping({"/mycase"})
    public ResponseEntity<Map> myCases(@RequestBody User userData) {
        return this.caseService.myCases(userData.getUsername());
    }

    @GetMapping({"/users/{caseId}/Report.pdf"})
    public ResponseEntity<Object> downloadReport(@PathVariable String caseId) {
        return this.caseService.downloadReport(caseId);
    }

    @PostMapping({"/getCaseDetails"})
    public ResponseEntity<Map> getCaseDetails(@RequestParam String caseId) {
        return this.caseService.getCaseDetails(caseId);
    }

    @PostMapping({"/uploadFiles"})
    public ResponseEntity<Map> uploadFiles(@RequestParam List<MultipartFile> file) {
        return this.caseService.uploadFiles(file);
    }

    @PostMapping({"/createCase"})
    public ResponseEntity<Map> createCase(@RequestParam String formId, @RequestParam String caseId) {
        return this.caseService.createCase(formId, caseId);
    }

    @PostMapping({"/getVideos"})
    public ResponseEntity<Map> getVideos(@RequestParam String caseId, @RequestParam int planNo) {
        return this.caseService.getVideos(caseId, planNo);
    }

    @GetMapping({"/GetUserCases/{userId}"})
    public ResponseEntity<Map> getUserCases(@PathVariable String userId) {
        return this.caseService.getUserData(userId);
    }
}
