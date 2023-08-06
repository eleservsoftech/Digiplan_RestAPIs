package com.digiplan.services;

import com.digiplan.entities.Demo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DemoService {

    ResponseEntity<Map> getAllDemoUsers();

    ResponseEntity<Map> getDemoUser(int id);

    ResponseEntity<Map> addDemoUser(Demo demo);

    ResponseEntity<Map> updateDemoUser(Demo demo);

    ResponseEntity<Map> deleteDemoUser(int id);
}
