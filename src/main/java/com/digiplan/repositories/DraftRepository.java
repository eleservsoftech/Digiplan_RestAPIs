package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Draft;

public interface DraftRepository extends JpaRepository<Draft, Integer> {

}
