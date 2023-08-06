package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
