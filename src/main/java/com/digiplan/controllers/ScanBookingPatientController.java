package com.digiplan.controllers;

import com.digiplan.entities.ScanBookingPatient;
import com.digiplan.services.ScanBookingPatientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scan-booking-patients")
public class ScanBookingPatientController {
    private final ScanBookingPatientService service;

    public ScanBookingPatientController(ScanBookingPatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllScanBookingPatients() {
        try {
            List<ScanBookingPatient> patients = service.getAllScanBookingPatients();
            return new ResponseEntity<>(getResponseData(patients, "Data retrieved successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(getErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{scanId}")
    public ResponseEntity<?> getScanBookingPatientById(@PathVariable String scanId) {
        try {
            ScanBookingPatient patient = service.getScanBookingPatientById(scanId);
            if (patient != null) {
                return new ResponseEntity<>(getResponseData(patient, "Data retrieved successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(getErrorResponse("Patient not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(getErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<?> createScanBookingPatient(@RequestBody ScanBookingPatient scanBookingPatient) {
        try {
            ScanBookingPatient createdPatient = service.createScanBookingPatient(scanBookingPatient);
            return new ResponseEntity<>(getResponseData(createdPatient, "Patient created successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(getErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{scanId}")
    public ResponseEntity<?> updateScanBookingPatient(
            @PathVariable String scanId,
            @RequestBody ScanBookingPatient updatedScanBookingPatient
    ) {
        try {
            ScanBookingPatient updatedPatient = service.updateScanBookingPatient(scanId, updatedScanBookingPatient);
            if (updatedPatient != null) {
                return new ResponseEntity<>(getResponseData(updatedPatient, "Patient updated successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(getErrorResponse("Patient not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(getErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{scanId}")
    public ResponseEntity<Map<String, Object>> deleteScanBookingPatient(@PathVariable String scanId) {
        try {
            boolean deleted = service.deleteScanBookingPatient(scanId);
            if (deleted) {
                Map<String, Object> response = getSuccessResponse("Patient deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> errorResponse = getErrorResponse("Patient not found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = getErrorResponse("Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Map<String, Object> getResponseData(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("status", HttpStatus.OK.value());
        return response;
    }

    private Map<String, Object> getSuccessResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    private Map<String, Object> getErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", message);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response;
    }

}
