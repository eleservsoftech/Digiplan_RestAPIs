package com.digiplan.controllers;

import com.digiplan.entities.CallbackRequestEntity;
import com.digiplan.services.CallbackRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
public class CallbackRequestController {

    @Autowired
    private CallbackRequestService callbackRequestService;
    @PostMapping("/callBackReq/create")
    public ResponseEntity<Map> addCallbackRequest(@RequestBody @Valid CallbackRequestEntity callbackRequest) {
        return this.callbackRequestService.createCallbackRequest(callbackRequest);
    }
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
        return this.callbackRequestService.getCallbackRequests();
    }
//    @DeleteMapping("/callBackReq/delete/{reqId}")
//    public  ResponseEntity<Map> deleteCallbackRequest(@PathVariable Integer reqId)   {
//        return  this.callbackRequestService.deleteCallbackRequest(reqId);
//    }

    @PutMapping("/callBackReq/del/{reqId}")
    public ResponseEntity<Map> deleteCallbackReq(@PathVariable Long reqId){
        return this.callbackRequestService.deleteCallReq(reqId);
    }
}


