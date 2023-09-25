package com.digiplan.services;

import com.digiplan.entities.CallbackRequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CallbackRequestService {
    public ResponseEntity<Map> createCallbackRequest(CallbackRequestEntity addCallbackRequestEntity);

    public ResponseEntity<Map> updateCallbackRequest(Long requestId, CallbackRequestEntity updateCallbackRequest);

    public  ResponseEntity<Map> getCallbackRequest(Long requestId);

    public  ResponseEntity<Map> getCallbackRequests();

    //public ResponseEntity<Map> deleteCallbackRequest(Long requestId);

    public ResponseEntity<Map> deleteCallReq(Long regId);



}

