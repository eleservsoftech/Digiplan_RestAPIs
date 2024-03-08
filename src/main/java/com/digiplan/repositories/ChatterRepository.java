package com.digiplan.repositories;

import com.digiplan.entities.CaseUploadEntity;
import com.digiplan.entities.Chatter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatterRepository extends JpaRepository<Chatter, Long> {

    @Query(value = "SELECT * FROM watts_digiplan.chatter where formid =:formId and ispublic=0" , nativeQuery = true)
    List <Chatter> getChatForDoctor (@Param("formId") Long formId);

    @Query(value = "SELECT * FROM watts_digiplan.chatter where formid =:formId" , nativeQuery = true)
    List <Chatter> getChatForSupport(@Param("formId") Long formId);
}
