package com.digiplan.controllers;

import com.digiplan.entities.Dealer;
import com.digiplan.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @GetMapping("/getDealer/{id}")
    public Map<String,Object> getDealer(@PathVariable Integer id) {
        Map<String,Object> map= new HashMap<>();

        try {
            if (this.dealerService.getDealer(id) != null) {
                map.put("status_code", "200");
                map.put("results", this.dealerService.getDealer(id));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or ID Invalid!");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }



    @GetMapping("/getAllDealers")
    public Map<String, Object> getAllDealers() {

        Map<String, Object> map=new HashMap<>();

        try {
            if (this.dealerService.getAllDealers().size() > 0) {
                map.put("status_code", "200");
                map.put("results", this.dealerService.getAllDealers());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        }
        catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }





    @PostMapping("/addDealer")
    public ResponseEntity<Dealer> addDraft(@RequestBody Dealer dealerData) {
        return new ResponseEntity<Dealer>(this.dealerService.addDealer(dealerData), HttpStatus.CREATED);
    }

    @PutMapping("/updateDealer/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Integer id, @RequestBody Dealer dealerData) {
        Dealer dealer = this.dealerService.updateDealer(id, dealerData);
        if (dealer != null)
            return new ResponseEntity<Dealer>(dealer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDealer/{id}")
    public ResponseEntity<String> deleteDealer(@PathVariable Integer id) {
        String status = this.dealerService.deleteDealer(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
