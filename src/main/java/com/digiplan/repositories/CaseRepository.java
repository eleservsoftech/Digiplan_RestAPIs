package com.digiplan.repositories;

import com.digiplan.entities.Cases;
import com.digiplan.entities.GetMyCaselistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Cases, String> {
    @Query(value = "select * from alignwise_cases where submittedby=:userId ", nativeQuery = true)
    List<Cases> getCasesByUserName(@Param("userId") String userId);

//    @Query(value = "SELECT * FROM alignwise_cases   where caseid=:caseId order by id desc limit 1", nativeQuery = true)
//    void getCasesById(@Param("caseId") String caseId);

    @Query(value = "SELECT * FROM alignwise_cases WHERE caseid = :caseId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Cases findByCaseId(@Param("caseId") String caseId);
<<<<<<< HEAD


=======
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
    @Query(value = " Call GetMyCaselist(?1,?2) ", nativeQuery = true)
    List<GetMyCaselistEntity> GetMyCaselist(String UserName, String activeCases);

}
