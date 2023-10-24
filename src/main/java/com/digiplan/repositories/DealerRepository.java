package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {

}
