package com.digiplan.controllers;

import com.digiplan.entities.IncompleteForm;
import com.digiplan.services.IncompleteFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class IncompleteFormController {

    @Autowired
    private IncompleteFormService incompleteFormService;

    @GetMapping("/getIncompleteForm/{id}")
    public ResponseEntity<Map> getIncompleteForm(@PathVariable Integer id) {
        return this.incompleteFormService.getIncompleteForm(id);
    }

    @GetMapping("/getAllIncompleteForms")
    public ResponseEntity<Map> getAllIncompleteForms() {
        return this.incompleteFormService.getAllIncompleteForms();
    }

    @PostMapping("/addIncompleteForm")
    public ResponseEntity<Map> addIncompleteForm(@RequestBody IncompleteForm incompleteFormData) {
        return this.incompleteFormService.addIncompleteForm(incompleteFormData);
    }

    @PutMapping("/updateIncompleteForm/{id}")
    public ResponseEntity<IncompleteForm> updateIncompleteForm(@PathVariable Integer id,
                                                               @RequestBody IncompleteForm incompleteFormData) {
        IncompleteForm incompleteForm = this.incompleteFormService.updateIncompleteForm(id, incompleteFormData);
        if (incompleteForm != null)
            return new ResponseEntity<IncompleteForm>(incompleteForm, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteIncompleteForm/{id}")
    public ResponseEntity<String> deleteIncompleteForm(@PathVariable Integer id) {
        String status = this.incompleteFormService.deleteIncompleteForm(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
