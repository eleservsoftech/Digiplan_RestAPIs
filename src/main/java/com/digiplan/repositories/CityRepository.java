package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.City;

public interface CityRepository extends JpaRepository<City, String> {

}
