package com.digiplan.repositories;

import com.digiplan.entities.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Logger, String> {
}
