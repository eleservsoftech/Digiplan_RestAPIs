package com.digiplan.servicesImpl;

import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.repositories.CallbackRequestRepository;
import com.digiplan.services.CallbackRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
