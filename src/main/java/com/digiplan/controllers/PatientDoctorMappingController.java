package com.digiplan.controllers;

import com.digiplan.entities.PatientDoctorMapping;
import com.digiplan.services.PatientDoctorMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class PatientDoctorMappingController {
    @Autowired
    private PatientDoctorMappingService patientDoctorMappingService;

    @GetMapping("/doctor")
    public ResponseEntity<Map<String, Object>> getAllMappings() {
        try {
            List<PatientDoctorMapping> mappings = patientDoctorMappingService.getAllMappings();
            Map<String, Object> response = new HashMap<>();
            response.put("data", mappings);
            response.put("message", "Data retrieved successfully");
            response.put("status", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Map<String, Object>> getMappingById(@PathVariable Long id) {
        try {
            PatientDoctorMapping mapping = patientDoctorMappingService.getMappingById(id);
            if (mapping != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", mapping);
                response.put("message", "Data retrieved successfully");
                response.put("status", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("data", null);
                response.put("message", "Data not found");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMapping(@PathVariable String id, @RequestBody PatientDoctorMapping updatedMapping) {
        try {
            PatientDoctorMapping result = patientDoctorMappingService.updateMapping(id, updatedMapping);
            if (result != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", result);
                response.put("message", "Data updated successfully");
                response.put("status", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("data", null);
                response.put("message", "Data not found");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctor/caseid/{caseId}")
    public ResponseEntity<Map<String, Object>> getMappingByCaseId(@PathVariable String caseId) {
        try {
            PatientDoctorMapping mapping = patientDoctorMappingService.getMappingByCaseId(caseId);
            if (mapping != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", mapping);
                response.put("message", "Data retrieved successfully");
                response.put("status", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("data", null);
                response.put("message", "Data not found");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMappingById(@PathVariable String id) {
        try {
            patientDoctorMappingService.deleteMappingById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Data deleted successfully");
            response.put("status", HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Data deleted successfully");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/doctor")
    public ResponseEntity<Map<String, Object>> createMapping(@RequestBody PatientDoctorMapping newMapping) {
        try {
            PatientDoctorMapping createdMapping = patientDoctorMappingService.createMapping(newMapping);
            Map<String, Object> response = new HashMap<>();
            response.put("data", createdMapping);
            response.put("message", "Mapping created successfully");
            response.put("status", HttpStatus.CREATED.value());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getMappingsByMobileOrEmail(
            @RequestParam(name = "ByMobileOrEmail", required = true) String mobileOrEmail) {
        try {
            List<PatientDoctorMapping> mappings = patientDoctorMappingService.getMappingsByMobileOrEmail(mobileOrEmail);
            if (!mappings.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", mappings);
                response.put("message", "Data retrieved successfully");
                response.put("status", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("data", Collections.emptyList());
                response.put("message", "No data found");
                response.put("status", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
