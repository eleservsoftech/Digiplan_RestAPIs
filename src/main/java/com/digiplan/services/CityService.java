package com.digiplan.services;

import com.digiplan.entities.City;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CityService {

    City getCity(String cityName);

    List<City> getAllCities();

    public ResponseEntity<Map> addCity(City addCity);

    //City updateCity(String cityName, City cityData);
    public ResponseEntity<Map> updateCity(String cityName, City cityData);

    String deleteCity(String cityName);
}
