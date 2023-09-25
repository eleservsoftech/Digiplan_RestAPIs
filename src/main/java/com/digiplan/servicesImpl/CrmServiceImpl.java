package com.digiplan.servicesImpl;

import com.digiplan.entities.Crm;
import com.digiplan.repositories.CrmRepository;
import com.digiplan.services.CrmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CrmServiceImpl implements CrmService {
    @Autowired
    private CrmRepository crmRepository;

    public ResponseEntity<Map> getCrm(Long crmId){
        HttpStatus status =null;
        Map<Object,Object> map = new HashMap<>();
        try {
            if (this.crmRepository.findById(crmId).isPresent()){
                map.put("status_code", "200");
                map.put("results", this.crmRepository.findById(crmId));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.crmRepository.findById(crmId));
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  new ResponseEntity<>(map,status);
    }

    @Override
    public  ResponseEntity<Map> getCrms(Crm crm){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {
            if (!this.crmRepository.findAll().isEmpty()) {
                map.put("status_code", "200");
                map.put("results", this.crmRepository.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.crmRepository.findAll());
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  new ResponseEntity<>(map,status);
    }

    @Override
    public  ResponseEntity<Map> getCrmByName(String crmName){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {
            Crm result = this.crmRepository.getCrmByName(crmName);
            if (result!=null){
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", result);
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code",HttpStatus.NOT_FOUND.value() );
                map.put("results", result);
                map.put("errorMessage", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  new ResponseEntity<>(map,status);
    }
}
