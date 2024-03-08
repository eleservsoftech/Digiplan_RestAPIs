package com.digiplan.servicesImpl;

import com.digiplan.entities.Chatter;
import com.digiplan.repositories.ChatterRepository;

import com.digiplan.services.ChatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
    public class ChatterServiceImpl implements ChatterService{

    @Autowired
    private ChatterRepository chatterRepository;

    @Override
    public ResponseEntity<Map> createChatter(Chatter chatter) {


     Chatter savedChatter = chatterRepository.save(chatter);

        Map<String, Object> response = new HashMap<>();
        response.put("Id", savedChatter.getId());
        response.put("message", "Chatter created successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public Chatter getChatterById(Long id) {
        Optional<Chatter> chatterOptional = chatterRepository.findById(id);
        return chatterOptional.orElse(null);
    }

    @Override
    public List<Chatter> getChatForDoctorByFormId(Long formId) {
        return chatterRepository.getChatForDoctor(formId);
    }

    @Override
    public List<Chatter> getChatForSupportByFormId(Long formId) {
        return chatterRepository.getChatForSupport(formId);
    }
}
