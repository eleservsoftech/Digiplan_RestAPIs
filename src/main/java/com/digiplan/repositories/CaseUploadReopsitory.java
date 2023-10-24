package com.digiplan.repositories;

import com.digiplan.entities.CaseUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CaseUploadReopsitory extends JpaRepository<CaseUploadEntity , Long> {
    //this select query is used to know the plan number of a particular case id is exists or not.
    @Query(value = "select * from alignwise_case_upload where case_id=:case_id and plan_no=:plan_no" , nativeQuery = true)
    CaseUploadEntity isPlanPresent(@Param("case_id") Long caseId, @Param("plan_no") String plan_no);

    //get all records
    @Query(value = "select * from alignwise_case_upload where is_deleted='N'  ", nativeQuery = true) //and status='Y'
    List<CaseUploadEntity> getCaseRecords();

    //get all records of a case id
    @Query(value = "select * from alignwise_case_upload where is_deleted='N' and status='Y' and case_id=:case_id ",  nativeQuery = true)
    List<CaseUploadEntity> getCaseIdRecords(@Param("case_id") Long caseId);

    //this update query is for used to update delete status,used for delete operation.
    @Transactional
    @Modifying
    @Query(value = "update alignwise_case_upload set is_deleted='Y' where case_id=:case_id and plan_no=:plan_no" , nativeQuery = true)
    int  caseStatusUpdate(@Param("case_id") Long caseId, @Param("plan_no") String plan_no);

    //UploadEntity findByCase_Id(Long case_id);
    CaseUploadEntity getById(Long case_id);
}
