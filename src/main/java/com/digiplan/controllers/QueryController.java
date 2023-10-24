package com.digiplan.controllers;

import com.digiplan.entities.Query;
import com.digiplan.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/getQuery/{queryId}")
    public Map<String,Object> getQuery(@PathVariable String queryId) {
        Map<String,Object> map=new HashMap<>();
        try {
            if (this.queryService.getQuery(queryId) != null) {
                map.put("status_code", "200");
                map.put("results", this.queryService.getQuery(queryId));
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or Draft Id is Invalid! ");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }

    @GetMapping("/getAllQueries")
    public Map<String,Object> getAllQueries() {
        Map<String,Object> map=new HashMap<>();
        try {
            if (this.queryService.getAllQueries() != null) {
                map.put("status_code", "200");
                map.put("results", this.queryService.getAllQueries());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found ! ");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return map;
    }

    @PostMapping("/contactus")
    public ResponseEntity<Map> addQuery(@RequestBody Query queryData) {
        return this.queryService.addQuery(queryData);
    }

    @PutMapping("/updateQuery/{queryId}")
    public ResponseEntity<Query> updateQuery(@PathVariable String queryId, @RequestBody Query queryData) {
        Query query = this.queryService.updateQuery(queryId, queryData);
        if (query != null)
            return new ResponseEntity<Query>(query, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteQuery/{queryId}")
    public ResponseEntity<String> deleteQuery(@PathVariable String queryId) {
        String status = this.queryService.deleteQuery(queryId);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
