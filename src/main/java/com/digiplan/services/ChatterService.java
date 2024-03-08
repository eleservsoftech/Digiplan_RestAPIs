package com.digiplan.services;

import com.digiplan.entities.Chatter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ChatterService {

    ResponseEntity<Map> createChatter(Chatter chatter);

    Chatter getChatterById(Long id);
    List<Chatter> getChatForDoctorByFormId (Long formId);
    List<Chatter> getChatForSupportByFormId (Long formId);
}
