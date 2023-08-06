package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.City;
import com.digiplan.repositories.CityRepository;

@SpringBootTest
public class CityServiceImplTests {

    @InjectMocks
    private CityServiceImpl cityServiceImpl;

    @Mock
    private CityRepository cityRepository;

    @Test
    public void test_getCity() {
        City city = new City("1", "Bhopal");
        Optional<City> retrievedData = Optional.of(new City("2", "Bhopal"));
        String cityName = "Bhopal";
        when(cityRepository.findById(cityName)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(cityRepository.getById(cityName)).thenReturn(city);
        assertEquals(cityName, cityServiceImpl.getCity(cityName).getCityName());
    }

    @Test
    public void test_getAllCities() {
        List<City> city = new ArrayList<>();
        city.add(new City("1", "Bhopal"));
        city.add(new City("2", "Chennai"));
        city.add(new City("3", "Surat"));
        when(cityRepository.findAll()).thenReturn(city);
        assertEquals(3, cityServiceImpl.getAllCities().size());
    }

    @Test
    public void test_addCity() {
        City city = new City("1", "Bhopal");
        when(cityRepository.saveAndFlush(city)).thenReturn(city);
        assertEquals(city, cityServiceImpl.addCity(city));
    }

    @Test
    public void test_updateCity() {
        City city = new City("1", "Bhopal");
        Optional<City> retrievedData = Optional.of(new City("2", "Bhopal"));
        String cityName = "Bhopal";
        when(cityRepository.findById(cityName)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(cityRepository.saveAndFlush(city)).thenReturn(city);
        assertEquals(city, cityServiceImpl.updateCity(cityName, city));
    }

    @Test
    public void test_deleteCity() {
        String cityName = "Bhopal";
        cityServiceImpl.deleteCity(cityName);
        verify(cityRepository, times(1)).deleteById(cityName);
    }

}
