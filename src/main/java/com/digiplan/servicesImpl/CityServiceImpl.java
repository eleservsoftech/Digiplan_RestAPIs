package com.digiplan.servicesImpl;

import com.digiplan.entities.City;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.CityRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public City getCity(String cityName) {
        City city = null;
        try {
            Optional<City> check = cityRepository.findById(cityName);
            if (check.isPresent())
                city = cityRepository.getById(cityName);
        } catch (Exception exception) {
            System.out.println("@getCity Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return city;
    }

    @Override
    public List<City> getAllCities() {
        List<City> citiesList = null;
        try {
            citiesList = cityRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllCities Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllCities", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return citiesList;
    }

//    @Override
//    public City addCity(City cityData) {
//        City city = null;
//        try {
//            city = cityRepository.saveAndFlush(cityData);
//        } catch (Exception exception) {
//            System.out.println("@addCity Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//        }
//        return city;
//    }
@Override
public ResponseEntity<Map> addCity(City addCity) {
    Map<Object, Object> map = new HashMap<>();
    HttpStatus status = null;
    City  cityObj = null;
    try {

        if(!addCity.getId().isEmpty() && !addCity.getCityName().isEmpty())
        {
            cityObj = cityRepository.save(addCity);
            map.put("status_code", "201");
            map.put("message", "City saved successfully");
            map.put("data", cityObj);
            status = HttpStatus.CREATED;
        }else {
            map.put("status_code", "400");
            map.put("message", "Either id is blank or city name!");
            map.put("data", "Data not saved");
            status = HttpStatus.BAD_REQUEST;
        }
    } catch (Exception ex) {
        map.put("status_code", "500");
        map.put("message", ex.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity(map, status);
}

//    @Override
//    public City updateCity(String cityName, City cityData) {
//        City city = null;
//        try {
//            Optional<City> check = cityRepository.findById(cityName);
//            if (check.isPresent())
//                city = cityRepository.saveAndFlush(cityData);
//        } catch (Exception exception) {
//            System.out.println("@updateCity Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//        }
//        return city;
//    }
@Override
public ResponseEntity<Map> updateCity(String cityName, City cityData) {
    Map<Object, Object> map = new HashMap<>();
    HttpStatus status = null;
    try {
        Optional<City> isCityExist = cityRepository.findById(cityName);
        if(isCityExist.isPresent()) {
        if(!cityData.getId().isEmpty() && !cityData.getCityName().isEmpty()) {
            City CityObj = cityRepository.saveAndFlush(cityData);
            map.put("status_code", "200");
            map.put("message", "City updated successfully");
            map.put("data", CityObj);
            status = HttpStatus.OK;
        }else {
            map.put("status_code", "400");
            map.put("message", "Either id is blank or city name!");
            map.put("data", "Data not saved");
            status = HttpStatus.BAD_REQUEST;
        }
        }
        else {
            map.put("status_code", "404");
            map.put("results", cityData);
            map.put("message", "City not updated!");
            status = HttpStatus.NOT_FOUND;
        }
    } catch (Exception ex) {
        map.put("status_code", "500");
        map.put("message", ex.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity(map, status);
}

    @Override
    public String deleteCity(String cityName) {
        String status = "";
        try {
            cityRepository.deleteById(cityName);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteCity Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
