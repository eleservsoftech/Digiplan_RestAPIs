package com.digiplan.controllers;

import com.digiplan.entities.PatientHistoryEntity;
import com.digiplan.entities.PatientHistoryMasterEntity;
import com.digiplan.services.PatientHistoryMasterService;
import com.digiplan.services.PatientHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class PatientHistoryMasterController {

    @Autowired
    private PatientHistoryMasterService patientHistoryService;
    @PostMapping("/addPatientMstrHistory")
    public ResponseEntity<Map> addPatientHistory(@RequestBody PatientHistoryMasterEntity patientHistory) {
        return this.patientHistoryService.addPatientHistoryMaster(patientHistory);
    }
    @PutMapping("/updatePatientMstrHistory/{id}")
    public ResponseEntity<Map> updatePatientHistory(@PathVariable Integer id, @RequestBody PatientHistoryMasterEntity PatientHistory) {
        return this.patientHistoryService.updatePatientHistoryMaster(id,PatientHistory);
    }
    @GetMapping("/getPatientMstrHistory/{id}")
    public  ResponseEntity<Map> getPatientHistory(@PathVariable Integer id)   {
        return  this.patientHistoryService.getPatientMasterHistory(id);
    }
    @GetMapping("/getPatientMstrHistories")
    public  ResponseEntity<Map> getPatientMasterHistories()   {
        return this.patientHistoryService.getPatientHistoryMasters();
    }
    @DeleteMapping("/deletePatientMstrHistory/{id}")
    public  ResponseEntity<Map> deletePatientMasterHistory(@PathVariable Integer id)   {
        return  this.patientHistoryService.deletePatientHistoryMaster(id);
    }
}
