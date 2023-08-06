package com.digiplan.services;

import com.digiplan.entities.City;

import java.util.List;

public interface CityService {

    City getCity(String cityName);

    List<City> getAllCities();

    City addCity(City cityData);

    City updateCity(String cityName, City cityData);

    String deleteCity(String cityName);
}
