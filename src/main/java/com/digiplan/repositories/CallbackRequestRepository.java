package com.digiplan.repositories;

import com.digiplan.entities.CallbackRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CallbackRequestRepository  extends JpaRepository<CallbackRequestEntity,Long> {

    @Transactional
    @Modifying
    @Query(value = " UPDATE callback_requests set isdelete = 1 where requestId =:reqId ", nativeQuery = true)
    int deleteCallBackReg(@Param("reqId") Long reqId);
}
