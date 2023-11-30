package com.digiplan.repositories;

<<<<<<< HEAD
import com.digiplan.entities.CallbackRequestEntity;
=======
import com.digiplan.entities.ActionOnMyRequest;
import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.entities.MyRequestData;
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db

@Repository
public interface CallbackRequestRepository  extends JpaRepository<CallbackRequestEntity,Long> {

    @Transactional
    @Modifying
    @Query(value = " UPDATE callback_requests set isdelete = 1 where requestId =:reqId ", nativeQuery = true)
    int deleteCallBackReg(@Param("reqId") Long reqId);
<<<<<<< HEAD


=======
// aman testing
    @Query(value = "CALL MyRequest(?1, ?2, ?3, ?4)", nativeQuery = true)
    List<MyRequestData> callMyRequest(String param1, String param2, String param3, String param4);


//    @Query(value = "call Request_Action(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
//    List<ActionOnMyRequest> callActionOnMyRequest(String param1, String param2, String param3, String param4, String param5);

    @Query(value = "CALL Request_Action(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    List<Object[]> callActionOnMyRequest(String param1, String param2, String param3, String param4, String param5);

    @Query(value = "CALL scan_request_Action(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    List<Object[]> callScanRequestAction(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8);

>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
}
