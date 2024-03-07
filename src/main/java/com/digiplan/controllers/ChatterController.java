package com.digiplan.controllers;

import com.digiplan.entities.Chatter;
import com.digiplan.services.ChatterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/chatter")

public class ChatterController {
    private final ChatterService chatterService;

    @Autowired
    public ChatterController(ChatterService chatterService) {
        this.chatterService = chatterService;
    }

    @PostMapping("/add")
    public ResponseEntity<Chatter> addChatter(@RequestBody Chatter chatter) {
        ResponseEntity<Map> response = chatterService.createChatter(chatter);
        Map<String, Object> responseBody = response.getBody();

        Long Id = (Long) responseBody.get("Id");
        String message = (String) responseBody.get("message");

        if (Id != null && message != null) {
            return new ResponseEntity<>(chatter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Chatter getChatterById(@PathVariable Long id) {
        return chatterService.getChatterById(id);
    }


}
