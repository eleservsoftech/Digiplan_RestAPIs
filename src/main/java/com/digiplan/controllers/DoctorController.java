package com.digiplan.controllers;

import com.digiplan.entities.Doctor;
import com.digiplan.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getDoctor/{caseId}")
    public Map<String, Object> getDoctor(@PathVariable String caseId) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.doctorService.getDoctor(caseId) != null) {
                map.put("HttpStatus", "200");
                map.put("results", this.doctorService.getDoctor(caseId));
                map.put("message", "Success");
            } else {
                map.put("HttpStatus", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Case Id is Invalid!");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;

    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<Map> getAllDoctors(@RequestParam String searchDoctor) {
        return this.doctorService.getAllDoctors(searchDoctor);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Map> addDoctor(@RequestBody Doctor doctorData) {
        return this.doctorService.addDoctor(doctorData);
    }

    @PutMapping("/updateDoctor/{caseId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String caseId, @RequestBody Doctor doctorData) {
        Doctor doctor = this.doctorService.updateDoctor(caseId, doctorData);
        if (doctor != null)
            return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDoctor/{caseId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable String caseId) {
        String status = this.doctorService.deleteDoctor(caseId);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
