package com.digiplan.controllers;

import com.digiplan.entities.AlignerWearingScheduleEntity;
import com.digiplan.services.AlignerWearingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class AlignerWearingScheduleController {

    @Autowired
    private AlignerWearingScheduleService aignerWearingScheduleService;
    @PostMapping("/addAlignerWearingSchedule")
    public ResponseEntity<Map> addAlignerWearingSchedule(@RequestBody AlignerWearingScheduleEntity alignerWearingSchedule) {
        return this.aignerWearingScheduleService.addAlignerWearingSchedule(alignerWearingSchedule);
    }
    @PutMapping("/updateAlignerWearingSchedule/{id}")
    public ResponseEntity<Map> updateAlignerWearingSchedule(@PathVariable Integer id, @RequestBody AlignerWearingScheduleEntity AlignerWearingSchedule) {
        return this.aignerWearingScheduleService.updateAlignerWearingSchedule(id,AlignerWearingSchedule);
    }
    @GetMapping("/getAlignerWearingSchedule/{id}")
    public  ResponseEntity<Map> getAlignerWearingSchedule(@PathVariable Integer id)   {
        return  this.aignerWearingScheduleService.getAlignerWearingSchedule(id);
    }
    @GetMapping("/getAlignerWearingSchedules")
    public  ResponseEntity<Map> getAlignerWearingSchedules()   {
        return this.aignerWearingScheduleService.getAlignerWearingSchedules();
    }
    @DeleteMapping("/deleteAlignerWearingSchedule/{id}")
    public  ResponseEntity<Map> deleteAlignerWearingSchedule(@PathVariable Integer id)   {
        return  this.aignerWearingScheduleService.deleteAlignerWearingSchedule(id);
    }
    @GetMapping("/getAlignerDispatchedData/{dispatchedId}")
    public ResponseEntity<Map> GetAlignerDispatchData(@PathVariable String dispatchedId) {
        return aignerWearingScheduleService.GetAlignerDispatchData(dispatchedId);
    }

    @GetMapping("/getDrAllCases/{Doctor_Name}")
    public ResponseEntity<Map> getDrAllCases(@PathVariable String Doctor_Name) {
        return aignerWearingScheduleService.getDrAllCases(Doctor_Name);
    }

    @PutMapping("/updateAlignerSchedule")
    public ResponseEntity<Map> updateAlignerSchedule(
            @RequestParam String case_id,
            @RequestParam String dispatchedId,
            @RequestParam(required = false) String aligner_no_u,
            @RequestParam(required = false) String aligner_no_l,
            @RequestParam String actualDate,
            @RequestParam(required = false) String remarks,
            @RequestParam(required = false) String user) {
        System.out.println("controller case_id: " + case_id + " dispatchedId: " + dispatchedId + " aligner_no_u: " + aligner_no_u + " aligner_no_l: " + aligner_no_l + " actualDate: " + actualDate + " remarks: " + remarks + " user: " + user);
        return aignerWearingScheduleService.updateAlignerSchedule(case_id,dispatchedId,aligner_no_u,
                aligner_no_l,actualDate,remarks,user);
    }
}
