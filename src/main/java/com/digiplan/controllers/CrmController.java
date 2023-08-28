package com.digiplan.controllers;

import com.digiplan.entities.Crm;
import com.digiplan.services.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class CrmController {

    @Autowired
    private CrmService crmService;

    @GetMapping("/getCrm/{crmId}")
    public ResponseEntity<Map> getCrm(@PathVariable Long crmId)   {
        return  this.crmService.getCrm(crmId);
    }
    @GetMapping("/getCrms")
    public  ResponseEntity<Map> getCrms(Crm Crm)   {
        return this.crmService.getCrms(Crm);
    }
}
