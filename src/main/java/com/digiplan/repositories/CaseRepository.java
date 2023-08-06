package com.digiplan.repositories;

import com.digiplan.entities.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Cases, String> {
}
