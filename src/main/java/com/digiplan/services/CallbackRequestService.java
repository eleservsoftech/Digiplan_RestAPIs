package com.digiplan.services;

import com.digiplan.entities.ActionOnMyRequest;
import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.entities.MyRequestData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CallbackRequestService {
    public ResponseEntity<Map> createCallbackRequest(CallbackRequestEntity addCallbackRequestEntity);

    public ResponseEntity<Map> updateCallbackRequest(Long requestId, CallbackRequestEntity updateCallbackRequest);

    public  ResponseEntity<Map> getCallbackRequest(Long requestId);

    public  ResponseEntity<Map> getCallbackRequests();

    //public ResponseEntity<Map> deleteCallbackRequest(Long requestId);

    public ResponseEntity<Map> deleteCallReq(Long regId);

    //aman testing
    List<MyRequestData> callMyRequest(String param1, String param2, String param3, String param4);

    List<Object[]> callActionOnMyRequest(String param1, String param2, String param3, String param4, String param5);

    List<Object[]> callScanRequestAction(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8);


//
}

