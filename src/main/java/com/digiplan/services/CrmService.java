package com.digiplan.services;

import com.digiplan.entities.Crm;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CrmService {

    public ResponseEntity<Map> getCrm(Long crmId);
    public  ResponseEntity<Map> getCrms(Crm crm);


}
