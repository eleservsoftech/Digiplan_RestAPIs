package com.digiplan.controllers;

import com.digiplan.entities.Image;
import com.digiplan.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/getImage/{id}")
    public Map<String,Object> getImage(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.imageService.getImage(id) != null) {
                map.put("status_code", "200");
                map.put("results", this.imageService.getImage(id));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Id is Invalid! ");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;
    }
/*    @GetMapping("/getImage/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        Image image = this.imageService.getImage(id);
        if (image != null)
            return new ResponseEntity<Image>(image, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/getAllImages")
    public Map<String,Object> getAllImages() {
        Map<String,Object> map=new HashMap<>();
        try {
            if (this.imageService.getAllImages() != null) {
                map.put("status_code", "200");
                map.put("results", this.imageService.getAllImages());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found! ");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
       // return this.imageService.getAllImages();
    }

    @PostMapping("/addImage")
    public ResponseEntity<Map> addImage(@RequestBody Image imageData) {
        return this.imageService.addImage(imageData);
    }


    @PutMapping("/updateImage/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Integer id, @RequestBody Image imageData) {
        Image image = this.imageService.updateImage(id, imageData);
        if (image != null)
            return new ResponseEntity<Image>(image, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        String status = this.imageService.deleteImage(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/uploadPPFFiles", consumes = "multipart/form-data")
    public ResponseEntity<Map> uploadPPFFiles(@RequestParam(required = false) Integer draftId, @RequestParam(required = false) Integer formId, @RequestParam MultipartFile side, @RequestParam MultipartFile front,
                                              @RequestParam MultipartFile frontSmiling, @RequestParam MultipartFile rightBuccal, @RequestParam MultipartFile leftBuccal,
                                              @RequestParam MultipartFile upperOcclusial, @RequestParam MultipartFile lowerOcclusial, @RequestParam MultipartFile frontal,
                                              @RequestParam MultipartFile opg, @RequestParam MultipartFile lateralCeph, @RequestParam MultipartFile other,
                                              @RequestParam MultipartFile pdf1, @RequestParam MultipartFile pdf2, @RequestParam String caseId, @RequestParam String patientName) {
        return this.imageService.uploadPPFFiles(draftId, formId, side, front, frontSmiling, rightBuccal, leftBuccal,
                upperOcclusial, lowerOcclusial, frontal, opg, lateralCeph, other, pdf1, pdf2, caseId, patientName);
    }

}
