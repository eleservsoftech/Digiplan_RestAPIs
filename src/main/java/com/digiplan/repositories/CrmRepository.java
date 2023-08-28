package com.digiplan.repositories;

import com.digiplan.entities.Crm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmRepository extends JpaRepository<Crm,Long> {
}
