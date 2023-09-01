package com.digiplan.controllers;

import com.digiplan.entities.City;
import com.digiplan.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/getCity/{cityName}")
    public Map<String,Object> getCity(@PathVariable String cityName) {

        Map<String, Object> map =new HashMap<>();

        try{
            if(this.cityService.getCity(cityName)!=null){
                map.put("status_code", "200");
                map.put("results", this.cityService.getCity(cityName));
                map.put("message", "Success");
            }else{
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found or City Name Invalid!");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
        return  map;
    }

    @GetMapping("/fetchcities")
    public List<City> getAllCities() {
        return this.cityService.getAllCities();
    }

    @PostMapping("/addCity")
    public ResponseEntity<Map> addState(@RequestBody City cityData) {
        return this.cityService.addCity(cityData);
    }

//    @PutMapping("/updateCity/{cityName}")
//    public ResponseEntity<City> updateCity(@PathVariable String cityName, @RequestBody City cityData) {
//        City city = this.cityService.updateCity(cityName, cityData);
//        if (city != null)
//            return new ResponseEntity<City>(city, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping("/updateCity/{cityName}")
    public ResponseEntity<Map> updateState(@PathVariable String cityName, @RequestBody City cityData) {
        return this.cityService.updateCity(cityName,cityData);
    }
    @DeleteMapping("/deleteCity/{cityName}")
    public ResponseEntity<String> deleteCity(@PathVariable String cityName) {
        String status = this.cityService.deleteCity(cityName);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
