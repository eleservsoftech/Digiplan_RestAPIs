package com.digiplan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@CrossOrigin(origins = {"*"})
@RestController
public class PDFViewController {

    @Autowired
    private Environment environment;

    @GetMapping(value = "/api/v1/pdf/brochure")
    public ResponseEntity<InputStreamResource> getBrochure() throws FileNotFoundException {
        try {
            File filePath = new File(this.environment.getProperty("file.pdf.brochure.location"));
            if (filePath.exists()) {
                new HttpHeaders().add("content-disposition", "inline;filename=" + filePath);
                return ResponseEntity.ok()
                        .headers(new HttpHeaders())
                        .contentLength(filePath.length())
                        .contentType(MediaType.parseMediaType("application/pdf"))
                        .body(new InputStreamResource(new FileInputStream(filePath)));
            }else
                return ResponseEntity.notFound().build();
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/api/v1/pdf/smile-book")
    public ResponseEntity<InputStreamResource> getSmileBook() throws FileNotFoundException {
        try {
            File filePath = new File(this.environment.getProperty("file.pdf.smilebook.location"));
            if (filePath.exists()) {
                new HttpHeaders().add("content-disposition", "inline;filename=" + filePath);
                return ResponseEntity.ok()
                        .headers(new HttpHeaders())
                        .contentLength(filePath.length())
                        .contentType(MediaType.parseMediaType("application/pdf"))
                        .body(new InputStreamResource(new FileInputStream(filePath)));
            }else
                return ResponseEntity.notFound().build();
           }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
