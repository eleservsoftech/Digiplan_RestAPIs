package com.digiplan.servicesImpl;

import com.digiplan.entities.ActionOnMyRequest;
import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.entities.MyRequestData;
import com.digiplan.repositories.CallbackRequestRepository;
import com.digiplan.services.CallbackRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CallbackRequestServicesImpl  implements CallbackRequestService {

    @Autowired
    private CallbackRequestRepository callbackRequestRepo;

    @Override
    public ResponseEntity<Map> createCallbackRequest(CallbackRequestEntity addCallbackRequestEntity) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            CallbackRequestEntity CallbackRequestEntityObj = callbackRequestRepo.save(addCallbackRequestEntity);
            map.put("status_code", HttpStatus.CREATED.value());
            map.put("message", "Call back request generated successfully");
            map.put("data", CallbackRequestEntityObj);
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Error @createCallbackRequest{} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    // aman testing
//    @Override
//    public List<MyRequestData> callMyRequest(String param1, String param2, String param3, String param4) {
//         return callbackRequestRepo.callMyRequest(param1, param2, param3, param4);
//
//    }

    @Override
    public List<MyRequestData> callMyRequest(String param1, String param2, String param3, String param4) {
        List<MyRequestData> requestData = callbackRequestRepo.callMyRequest(param1, param2, param3, param4);

        if (requestData != null && !requestData.isEmpty()) {
            return requestData;
        }

        return new ArrayList<>();
    }


    @Override
    public List<Object[]> callActionOnMyRequest(String param1, String param2, String param3, String param4, String param5) {
        return callbackRequestRepo.callActionOnMyRequest(param1, param2, param3, param4, param5);
    }

    @Override
    public List<Object[]> callScanRequestAction(String param1, String param2, String param3, String param4, String param5, String param6, String param7, String param8) {
        return callbackRequestRepo.callScanRequestAction(param1, param2, param3, param4, param5, param6, param7, param8);
    }

//    @Override
//    public List<ActionOnMyRequest> callActionOnMyRequest(String request_id, String Request_type, String request_status, String wattsuser, String wattsuser_remarks) {
//        Map<String, Object> responseMap = new HashMap<>();
//        HttpStatus status = HttpStatus.OK;
//
//        try {
//            ActionOnMyRequest result = (ActionOnMyRequest) callbackRequestRepo.callActionOnMyRequest(request_id, Request_type, request_status, wattsuser, wattsuser_remarks);
//
//            if (result != null) {
//                responseMap.put("status", HttpStatus.OK.value());
//                responseMap.put("message", "Request Action Data Updated Successfully");
//            } else {
//                responseMap.put("status", HttpStatus.NOT_FOUND.value());
//                responseMap.put("message", "No Data Updated found!");
//                status = HttpStatus.NOT_FOUND;
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//            responseMap.put("message", "Internal Server Error");
//            responseMap.put("error", exception.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        return (List<ActionOnMyRequest>) new ResponseEntity<>(responseMap, status);
//    }



    @Override
    public ResponseEntity<Map> updateCallbackRequest(Long requestId, CallbackRequestEntity updateCallbackRequest) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<CallbackRequestEntity> isCallbackRequestEntityExist = callbackRequestRepo.findById(requestId);
            if(isCallbackRequestEntityExist.isPresent()) {
                CallbackRequestEntity CallbackRequestEntityObj = callbackRequestRepo.saveAndFlush(updateCallbackRequest);
                map.put("status_code", HttpStatus.OK.value());
                map.put("message", "Call back request updated successfully");
                map.put("data", CallbackRequestEntityObj);
                status = HttpStatus.OK;
            }
            else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", updateCallbackRequest);
                map.put("message", "data not updated!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            map.put("status_code",  HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Error @updateCallbackRequest{} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    public  ResponseEntity<Map> getCallbackRequest(Long requestId){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {
            if (this.callbackRequestRepo.findById(requestId).isPresent()){
                map.put("status_code",  HttpStatus.OK.value());
                map.put("results", this.callbackRequestRepo.findById(requestId));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code",  HttpStatus.NOT_FOUND.value());
                map.put("results", this.callbackRequestRepo.findById(requestId));
                map.put("errorMessage", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Error @getCallbackRequest{} " + e.getMessage());
        }
        return  new ResponseEntity<>(map,status);
    }
    @Override
    public  ResponseEntity<Map> getCallbackRequests(){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {
            List<CallbackRequestEntity> callbackRequests = callbackRequestRepo.findAll();
            if (!callbackRequests.isEmpty()) {
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", this.callbackRequestRepo.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", this.callbackRequestRepo.findAll());
                map.put("errorMessage", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Error @getCallbackRequests{} " + e.getMessage());
        }
        return  new ResponseEntity<>(map,status);
    }
//    @Override
//    public ResponseEntity<Map> deleteCallbackRequest(Integer requestId) {
//        Map<Object, Object> map = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            if (this.callbackRequeestRepo.existsById(requestId)) {
//                this.callbackRequeestRepo.deleteById(requestId);
//                map.put("status_code", "200");
//                map.put("results", requestId);
//                map.put("message", "Deleted");
//                status = HttpStatus.OK;
//            } else {
//                map.put("status_code", "204");
//                map.put("results", requestId);
//                map.put("errorMessage", "Not Deleted!");
//                status = HttpStatus.NOT_FOUND;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            map.put("status_code", "500");
//            map.put("error", ex.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity(map, status);
//    }


    @Override
    public ResponseEntity<Map> deleteCallReq(Long regId){
        Map<String,Object> map = new HashMap<>();
        HttpStatus status = null;
        try{
            int cnt = callbackRequestRepo.deleteCallBackReg(regId);
            System.out.println("cnt: "+cnt);
            if (cnt!=0){
                map.put("status_code", 200);
                map.put("results", cnt);
                map.put("message","Successfully Deleted");
                status = HttpStatus.OK;
            }
            else {
                map.put("status_code", 404);
                map.put("errorMessage", "No content!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            map.put("status_code", 500);
            map.put("error", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("Error @updateCallbackRequest{} " + ex.getMessage());        }
        return new ResponseEntity<>(map,status);
    }
}
