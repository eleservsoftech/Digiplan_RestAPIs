package com.digiplan.repositories;

import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.services.MidAssessmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MidAssessmentRepository extends JpaRepository<MidAssessmentEntity,Long> {

    @Query(value = " SELECT * FROM mid_assessment where request_id =:request_id", nativeQuery = true)
    MidAssessmentEntity findByImage(@Param("request_id") String request_id);
}
