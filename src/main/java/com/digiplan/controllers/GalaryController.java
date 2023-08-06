package com.digiplan.controllers;

import com.digiplan.entities.Gallery;
import com.digiplan.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class GalaryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/getGalleryData/{caseId}")
    public ResponseEntity<Gallery> getGalleryData(@PathVariable String caseId) {
        Gallery gallery = this.galleryService.getGalleryData(caseId);
        if (gallery != null)
            return new ResponseEntity<Gallery>(gallery, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllGalleryData")
    public List<Gallery> getAllGalleryData() {
        return this.galleryService.getAllGalleryData();
    }

    @PostMapping("/addGalleryData")
    public ResponseEntity<Gallery> addGalleryData(@RequestBody Gallery galleryData) {
        return new ResponseEntity<Gallery>(this.galleryService.addGalleryData(galleryData), HttpStatus.CREATED);
    }

    @PutMapping("/updateGalleryData/{caseId}")
    public ResponseEntity<Gallery> updateGalleryData(@PathVariable String caseId, @RequestBody Gallery galleryData) {
        Gallery gallery = this.galleryService.updateGalleryData(caseId, galleryData);
        if (gallery != null)
            return new ResponseEntity<Gallery>(gallery, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteGalleryData/{caseId}")
    public ResponseEntity<String> deleteGalleryData(@PathVariable String caseId) {
        String status = this.galleryService.deleteGalleryData(caseId);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/samples")
    public ResponseEntity<Map> getSamples() {
        return this.galleryService.getSamples();
    }

    @PostMapping("/sampleFilterData")
    public ResponseEntity<Map> getSampleFilterData(@RequestParam String caseType, @RequestParam String caseCategory, @RequestParam String gender) {
        return this.galleryService.getSampleFilterData(caseType, caseCategory, gender);
    }

}
