package com.digiplan.repositories;

import com.digiplan.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, String> {
    @Query(value = "select * from gallery where case_type=:caseType and case_category like %:caseCategory% and gender like :gender%", nativeQuery = true)
    List<Gallery> getSampleDataList(@Param("caseType") String caseType, @Param("caseCategory") String caseCategory, @Param("gender") String gender);
}
