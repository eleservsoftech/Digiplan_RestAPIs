package com.digiplan.controllers;

<<<<<<< HEAD
import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.services.CallbackRequestService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.digiplan.entities.ActionOnMyRequest;
import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.entities.MyRequestData;
import com.digiplan.services.CallbackRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
<<<<<<< HEAD
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
public class CallbackRequestController {

    @Autowired
    private CallbackRequestService callbackRequestService;
    @PostMapping("/callBackReq/create")
    public ResponseEntity<Map> addCallbackRequest(@RequestBody @Valid CallbackRequestEntity callbackRequest) {
        return this.callbackRequestService.createCallbackRequest(callbackRequest);
    }
<<<<<<< HEAD
    @PutMapping("/callBackReq/update/{reqId}")
    public ResponseEntity<Map> updateCallbackRequest(@PathVariable Long reqId,@RequestBody @Valid CallbackRequestEntity callbackRequest) {
        return this.callbackRequestService.updateCallbackRequest(reqId,callbackRequest);
    }
    @GetMapping("/callBackReq/getCall/{reqId}")
    public  ResponseEntity<Map> getCallbackRequest(@PathVariable Long reqId)   {
        return  this.callbackRequestService.getCallbackRequest(reqId);
    }
    @GetMapping("/callBackRequest/getCalls")
    public  ResponseEntity<Map> getCallbackRequests()   {
=======

    @PutMapping("/callBackReq/update/{reqId}")
    public ResponseEntity<Map> updateCallbackRequest(@PathVariable Long reqId, @RequestBody @Valid CallbackRequestEntity callbackRequest) {
        return this.callbackRequestService.updateCallbackRequest(reqId, callbackRequest);
    }

    @GetMapping("/callBackReq/getCall/{reqId}")
    public ResponseEntity<Map> getCallbackRequest(@PathVariable Long reqId) {
        return this.callbackRequestService.getCallbackRequest(reqId);
    }

    @GetMapping("/callBackRequest/getCalls")
    public ResponseEntity<Map> getCallbackRequests() {
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
        return this.callbackRequestService.getCallbackRequests();
    }
//    @DeleteMapping("/callBackReq/delete/{reqId}")
//    public  ResponseEntity<Map> deleteCallbackRequest(@PathVariable Integer reqId)   {
//        return  this.callbackRequestService.deleteCallbackRequest(reqId);
//    }

    @PutMapping("/callBackReq/del/{reqId}")
<<<<<<< HEAD
    public ResponseEntity<Map> deleteCallbackReq(@PathVariable Long reqId){
        return this.callbackRequestService.deleteCallReq(reqId);
    }

=======
    public ResponseEntity<Map> deleteCallbackReq(@PathVariable Long reqId) {
        return this.callbackRequestService.deleteCallReq(reqId);
    }

    //aman testing
    @GetMapping("/callMyRequest")
    public ResponseEntity<Map<String, Object>> callMyRequest(
            @RequestParam String user,
            @RequestParam String request_status,
            @RequestParam String requesttype,
            @RequestParam String sub_request) {
        try {
            List<MyRequestData> requestData = callbackRequestService.callMyRequest(user, request_status, requesttype, sub_request);

            Map<String, Object> response = new HashMap<>();

            if (requestData != null && !requestData.isEmpty()) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "OK");
                response.put("data", requestData);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Not Found");
                response.put("data", new ArrayList<>());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("data", new ArrayList<>());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/callActionOnMyRequest")
    public List<Object[]> callActionOnMyRequest(@RequestParam String request_id, String Request_type, String request_status, String wattsuser, String wattsuser_remarks) {
        return callbackRequestService.callActionOnMyRequest(request_id, Request_type, request_status, wattsuser, wattsuser_remarks);

    }

    @PutMapping("/callScanRequestAction")
    public List<Object[]> callScanRequestAction(@RequestParam String request_id, String Request_type, String request_status, String wattsuser, String wattsuser_remarks, String have_you_decide_with_doctor, String reschedule_date, String reschedule_time) {
        return callbackRequestService.callScanRequestAction(request_id, Request_type, request_status, wattsuser, wattsuser_remarks, have_you_decide_with_doctor, reschedule_date, reschedule_time  );

    }

>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
}


