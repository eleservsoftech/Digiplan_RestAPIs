package com.digiplan.services;

import com.digiplan.entities.Chatter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ChatterService {

    ResponseEntity<Map> createChatter(Chatter chatter);

    Chatter getChatterById(Long id);
}
