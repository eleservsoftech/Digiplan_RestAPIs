package com.digiplan.servicesImpl;

import com.digiplan.entities.City;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.CityRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public City addCity(City cityData) {
        City city = null;
        try {
            city = cityRepository.saveAndFlush(cityData);
        } catch (Exception exception) {
            System.out.println("@addCity Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return city;
    }

    @Override
    public City updateCity(String cityName, City cityData) {
        City city = null;
        try {
            Optional<City> check = cityRepository.findById(cityName);
            if (check.isPresent())
                city = cityRepository.saveAndFlush(cityData);
        } catch (Exception exception) {
            System.out.println("@updateCity Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateCity", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return city;
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
