package com.digiplan.repositories;

import com.digiplan.entities.Chatter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatterRepository extends JpaRepository<Chatter, Long> {
}
