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
}
