package com.digiplan.controllers;

import com.digiplan.entities.AlignerWearingScheduleEntity;
import com.digiplan.services.AlignerWearingScheduleService;
<<<<<<< HEAD
import com.digiplan.servicesImpl.AlignerWearingScheduleServiceImpl;
=======
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.validation.Valid;
import java.util.Date;
=======
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class AlignerWearingScheduleController {

    @Autowired
    private AlignerWearingScheduleService aignerWearingScheduleService;
    @PostMapping("/addAlignerWearingSchedule")
<<<<<<< HEAD
    public ResponseEntity<Map> addAlignerWearingSchedule(@RequestBody @Valid AlignerWearingScheduleEntity alignerWearingSchedule) {
        return this.aignerWearingScheduleService.addAlignerWearingSchedule(alignerWearingSchedule);
    }
    @PutMapping("/updateAlignerWearingSchedule/{id}")
    public ResponseEntity<Map> updateAlignerWearingSchedule(@PathVariable Integer id, @RequestBody @Valid AlignerWearingScheduleEntity AlignerWearingSchedule) {
=======
    public ResponseEntity<Map> addAlignerWearingSchedule(@RequestBody AlignerWearingScheduleEntity alignerWearingSchedule) {
        return this.aignerWearingScheduleService.addAlignerWearingSchedule(alignerWearingSchedule);
    }
    @PutMapping("/updateAlignerWearingSchedule/{id}")
    public ResponseEntity<Map> updateAlignerWearingSchedule(@PathVariable Integer id, @RequestBody AlignerWearingScheduleEntity AlignerWearingSchedule) {
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
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
<<<<<<< HEAD

=======
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
    @GetMapping("/getAlignerDispatchedData/{dispatchedId}")
    public ResponseEntity<Map> GetAlignerDispatchData(@PathVariable String dispatchedId) {
        return aignerWearingScheduleService.GetAlignerDispatchData(dispatchedId);
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
<<<<<<< HEAD

=======
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
}
