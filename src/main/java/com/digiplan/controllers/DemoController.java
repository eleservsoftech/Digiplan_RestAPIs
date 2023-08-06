package com.digiplan.controllers;

import com.digiplan.entities.Demo;
import com.digiplan.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/getAllDemoUsers")
    public ResponseEntity<Map> getAllDemoUsers() {
        return demoService.getAllDemoUsers();
    }

    @GetMapping("/getDemoUser/{id}")
    public ResponseEntity<Map> getDemoUser(@PathVariable int id) {
        return demoService.getDemoUser(id);
    }

    @PostMapping("/addDemoUser")
    public ResponseEntity<Map> addDemoUser(@RequestBody Demo demo) {
        return demoService.addDemoUser(demo);
    }

    @PutMapping("/updateDemoUser")
    public ResponseEntity<Map> updateDemoUser(@RequestBody Demo demo) {
        return demoService.updateDemoUser(demo);
    }

    @DeleteMapping("/deleteDemoUser/{id}")
    public ResponseEntity<Map> deleteDemoUser(@PathVariable int id) {
        return demoService.deleteDemoUser(id);
    }
}
