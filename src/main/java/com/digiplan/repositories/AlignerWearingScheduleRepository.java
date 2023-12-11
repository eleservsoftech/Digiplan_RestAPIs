package com.digiplan.repositories;

import com.digiplan.entities.AlignerDispatchData;
import com.digiplan.entities.AlignerWearingScheduleEntity;
import com.digiplan.entities.Getdrallcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AlignerWearingScheduleRepository extends JpaRepository<AlignerWearingScheduleEntity,Integer> {
    @Query(value = " Call get_aligner_schedule(?1) ", nativeQuery = true)
    List<AlignerDispatchData> alignerDispatchData(String dispatchedId);

    @Query(value = " CALL Getdrallcase(?1) ", nativeQuery = true)
    List<Getdrallcase> getdrallcase(String CaseId);

    @Query(value = "Select * from aligner_wearing_schedule where case_id=:caseId limit 1", nativeQuery = true)
    AlignerWearingScheduleEntity findByCaseId(@Param("caseId") String caseId);

    @Transactional
    @Modifying
    @Query(value = " Call update_aligner_schedule(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void UpdateAlignerSchedule(String caseId, String dispatchedId, String alignerNoU, String alignerNoL, String actualDate, String remarks, String sign);
}
