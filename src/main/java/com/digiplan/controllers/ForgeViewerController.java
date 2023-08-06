package com.digiplan.controllers;

import com.digiplan.entities.ForgeViewer;
import com.digiplan.services.ForgeViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ForgeViewerController {

    @Autowired
    private ForgeViewerService forgeViewerService;

    @GetMapping("/getForgeViewer/{id}")
    public Map<String,Object> getForgeViewer(@PathVariable Integer id) {
        Map<String,Object> map =new HashMap<>();
        try {
            if (this.forgeViewerService.getForgeViewer(id) != null) {
                map.put("status_code", "200");
                map.put("results", this.forgeViewerService.getForgeViewer(id));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Id is Invalid!");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }

    @GetMapping("/getAllForgeViewers")
    public Map<String,Object> getAllForgeViewers() {
        Map<String,Object> map =new HashMap<>();
        try {
            if (this.forgeViewerService.getAllForgeViewers()!= null) {
                map.put("status_code", "200");
                map.put("results", this.forgeViewerService.getAllForgeViewers());
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
        //return this.forgeViewerService.getAllForgeViewers();
    }

    @PostMapping("/addForgeViewer")
    public ResponseEntity<ForgeViewer> addForgeViewer(@RequestBody ForgeViewer forgeViewerData) {
        return new ResponseEntity<ForgeViewer>(this.forgeViewerService.addForgeViewer(forgeViewerData),
                HttpStatus.CREATED);
    }

    @PutMapping("/updateForgeViewer/{id}")
    public ResponseEntity<ForgeViewer> updateForgeViewer(@PathVariable Integer id,
                                                         @RequestBody ForgeViewer forgeViewerData) {
        ForgeViewer forgeViewer = this.forgeViewerService.updateForgeViewer(id, forgeViewerData);
        if (forgeViewer != null)
            return new ResponseEntity<ForgeViewer>(forgeViewer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteForgeViewer/{id}")
    public ResponseEntity<String> deleteForgeViewer(@PathVariable Integer id) {
        String status = this.forgeViewerService.deleteForgeViewer(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
