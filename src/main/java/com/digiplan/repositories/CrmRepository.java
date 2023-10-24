package com.digiplan.repositories;

import com.digiplan.entities.Crm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmRepository extends JpaRepository<Crm,Long> {

    // get record by case id
    @Query(value = " SELECT * from alignwise_crm where crm_name=:crmName",nativeQuery = true)
    Crm  getCrmByName(@Param("crmName") String crmName);
}
