package com.digiplan.controllers;

import com.digiplan.entities.Patient;
import com.digiplan.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/getPatient/{caseId}")
    public Map<String,Object> getPatient(@PathVariable String caseId) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.patientService.getPatient(caseId) != null) {
                map.put("status_code", "200");
                map.put("results", this.patientService.getPatient(caseId));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case Id is Invalid! ");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;
        /*Patient patient = this.patientService.getPatient(caseId);
        if (patient != null)
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
    }

/*    @GetMapping("/getPatient/{caseId}")
    public ResponseEntity<Patient> getPatient(@PathVariable String caseId) {
        Patient patient = this.patientService.getPatient(caseId);
        if (patient != null)
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/getAllPatients")
    public Map<String,Object> getAllPatients() {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.patientService.getAllPatients()!= null) {
                map.put("status_code", "200");
                map.put("results", this.patientService.getAllPatients());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found! ");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patientData) {
        return new ResponseEntity<Patient>(this.patientService.addPatient(patientData), HttpStatus.CREATED);
    }

    @PutMapping("/updatePatient/{caseId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String caseId, @RequestBody Patient patientData) {
        Patient patient = this.patientService.updatePatient(caseId, patientData);
        if (patient != null)
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePatient/{caseId}")
    public ResponseEntity<String> deletePatient(@PathVariable String caseId) {
        String status = this.patientService.deletePatient(caseId);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
