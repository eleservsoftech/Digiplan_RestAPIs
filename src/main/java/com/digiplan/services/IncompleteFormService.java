package com.digiplan.services;

import com.digiplan.entities.IncompleteForm;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IncompleteFormService {

    ResponseEntity<Map> getIncompleteForm(Integer id);

    ResponseEntity<Map> getAllIncompleteForms();

    ResponseEntity<Map> addIncompleteForm(IncompleteForm incompleteFormData);

    IncompleteForm updateIncompleteForm(Integer id, IncompleteForm incompleteFormData);

    String deleteIncompleteForm(Integer id);
}
