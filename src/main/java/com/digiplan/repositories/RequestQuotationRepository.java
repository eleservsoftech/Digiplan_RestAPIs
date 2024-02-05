package com.digiplan.repositories;

import com.digiplan.entities.MidAssessmentEntity;
import com.digiplan.entities.RequestQuotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



    public interface RequestQuotationRepository extends JpaRepository<RequestQuotationEntity, Long> {


    @Query(value = " SELECT * FROM request_quotation where form_id =:form_id", nativeQuery = true)
    RequestQuotationEntity findByImage(@Param("form_id") String form_id);
}
