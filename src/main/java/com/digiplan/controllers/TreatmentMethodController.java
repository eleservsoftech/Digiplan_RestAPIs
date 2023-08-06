package com.digiplan.controllers;

import com.digiplan.entities.TreatmentMethod;
import com.digiplan.services.TreatmentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class TreatmentMethodController {

    @Autowired
    private TreatmentMethodService treatmentMethodService;

    @GetMapping("/getTreatmentMethodData/{id}")
    public ResponseEntity<TreatmentMethod> getTreatmentMethodData(@PathVariable String id) {
        TreatmentMethod treatmentMethod = this.treatmentMethodService.getTreatmentMethodData(id);
        if (treatmentMethod != null)
            return new ResponseEntity<TreatmentMethod>(treatmentMethod, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllTreatmentMethodData")
    public Map<String,Object> getAllTreatmentMethodData() {
        Map<String,Object> map=new HashMap<>();

        try {
            if (this.treatmentMethodService.getAllTreatmentMethodData().size() > 0) {
                map.put("status_code", "200");
                map.put("results", this.treatmentMethodService.getAllTreatmentMethodData());
                map.put("message", "Success");

            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }



    @PostMapping("/addTreatmentMethodData")
    public ResponseEntity<TreatmentMethod> addTreatmentMethodData(@RequestBody TreatmentMethod treatmentMethodData) {
        return new ResponseEntity<TreatmentMethod>(
                this.treatmentMethodService.addTreatmentMethodData(treatmentMethodData), HttpStatus.CREATED);
    }

    @PutMapping("/updateTreatmentMethodData/{id}")
    public ResponseEntity<TreatmentMethod> updateTreatmentMethodData(@PathVariable String id,
                                                                     @RequestBody TreatmentMethod treatmentMethodData) {
        TreatmentMethod treatmentMethod = this.treatmentMethodService.updateTreatmentMethodData(id,
                treatmentMethodData);
        if (treatmentMethod != null)
            return new ResponseEntity<TreatmentMethod>(treatmentMethod, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteTreatmentMethodData/{id}")
    public ResponseEntity<String> deleteTreatmentMethodData(@PathVariable String id) {
        String status = this.treatmentMethodService.deleteTreatmentMethodData(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
