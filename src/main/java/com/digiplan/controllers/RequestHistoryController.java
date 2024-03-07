package com.digiplan.controllers;

import com.digiplan.entities.RequestHistory;
import com.digiplan.services.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/requestHistory")
public class RequestHistoryController {

    private final RequestHistoryService requestHistoryService;

    @Autowired
    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<RequestHistory> addRequestHistory(@RequestBody RequestHistory requestHistory) {
        ResponseEntity<Map> response = requestHistoryService.createRequestHistory(requestHistory);
        Map<String, Object> responseBody = response.getBody();

        Long Id = (Long) responseBody.get("Id");
        String message = (String) responseBody.get("message");

        if (Id != null && message != null) {
            return new ResponseEntity<>(requestHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public RequestHistory getRequestHistoryById(@PathVariable Long id) {
        return requestHistoryService.getRequestHistoryById(id);
    }

}
