package com.digiplan.repositories;

import com.digiplan.entities.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Cases, String> {
    @Query(value = "select * from alignwise_cases where submittedby=:userId ", nativeQuery = true)
    List<Cases> getCasesByUserName(@Param("userId") String userId);
}
