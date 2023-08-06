package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Query;

public interface QueryRepository extends JpaRepository<Query, String> {

}
