package com.digiplan.controllers;

import com.digiplan.entities.PatientHistoryEntity;
import com.digiplan.services.PatientHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController

public class PatientHistoryController {

    @Autowired
    private PatientHistoryService patientHistoryService;
    @PostMapping("/addPatientHistory")
    public ResponseEntity<Map> addPatientHistory(@RequestBody PatientHistoryEntity patientHistory) {
        return this.patientHistoryService.addPatientHistory(patientHistory);
    }
    @PutMapping("/updatePatientHistory/{id}")
    public ResponseEntity<Map> updatePatientHistory(@PathVariable Long id, @RequestBody PatientHistoryEntity PatientHistory) {
        return this.patientHistoryService.updatePatientHistory(id,PatientHistory);
    }
    @GetMapping("/getPatientHistory/{id}")
    public  ResponseEntity<Map> getPatientHistory(@PathVariable Long id)   {
        return  this.patientHistoryService.getPatientHistory(id);
    }
    @GetMapping("/getPatientHistorys")
    public  ResponseEntity<Map> getPatientHistories()   {
        System.out.println("controller getPatientHistorys");
        return this.patientHistoryService.getPatientHistorys();
    }
    @DeleteMapping("/deletePatientHistory/{id}")
    public  ResponseEntity<Map> deletePatientHistory(@PathVariable Long id)   {
        return  this.patientHistoryService.deletePatientHistory(id);
    }
}
