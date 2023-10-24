package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.IncompleteForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncompleteFormRepository extends JpaRepository<IncompleteForm, Integer> {


    //@Query(value = "SELECT form_id,patientname,remarks,submittedby,submittedon FROM incompleteform where flag='Y' AND submittedby=:submittedby", nativeQuery = true)
    @Query(value = "SELECT * FROM incompleteform where flag='Y' AND submittedby=:submittedby", nativeQuery = true)
    List<IncompleteForm> getUserPendingCases(@Param("submittedby") String submittedby);
}



