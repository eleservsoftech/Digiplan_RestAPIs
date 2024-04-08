package com.digiplan.controllers;

import com.digiplan.entities.Notification;
import com.digiplan.entities.NotificationRequest;
import com.digiplan.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        Map<String, Object> response = new HashMap<>();
        response.put("data", notifications);
        response.put("message", "Data Received Successfully");
        response.put("status", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        Map<String, Object> response = new HashMap<>();
        if (notification.isPresent()) {
            response.put("data", notification.get());
            response.put("message", "Data Received Successfully");
            response.put("status", HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Data not found");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> saveNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.saveNotification(notification);
        Map<String, Object> response = new HashMap<>();
        response.put("data", savedNotification);
        response.put("message", "Data saved successfully");
        response.put("status", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateNotification(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        Notification notification = notificationService.updateNotification(id, updatedNotification);
        Map<String, Object> response = new HashMap<>();
        if (notification != null) {
            response.put("data", notification);
            response.put("message", "Data updated successfully");
            response.put("status", HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Data not found");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    @DeleteMapping("/{id}")
//    public void deleteNotification(@PathVariable Long id) {
//        notificationService.deleteNotification(id);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNotification(@PathVariable Long id) {
        Notification deletedNotification = notificationService.deleteNotification(id);

        if (deletedNotification != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Data deleted successfully");
            response.put("status", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Data not found with id: " + id);
            response.put("status", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    @PostMapping("/call")
//    public List<Object[]> callNotificationList(@RequestBody NotificationRequest request) {
//        return notificationService.callNotificationList(request.getFromDate(), request.getToDate());
//    }

    @PostMapping("/call")
    public ResponseEntity<Map<String, Object>> callNotificationList(@RequestBody NotificationRequest request) {
        try {
            List<Object[]> result = notificationService.callNotificationList(request.getFromDate(), request.getToDate());
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            response.put("data", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/callNotificationList/{user_id}")
    public ResponseEntity<Map<String, Object>> callDoctorNotification(@PathVariable String user_id) {
        try {
            List<Object[]> result = notificationService.callDoctorNotification(user_id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            response.put("data", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notificationupdateisread/{user_id}")
    public ResponseEntity<Map<String, Object>> callnotificationupdateisread(@PathVariable String user_id) {
        try {
             notificationService.callNotificationupdateisread(user_id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/noOfNotificationPending/{user_id}")
    public ResponseEntity<Map<String, Object>> noOfNotificationPending(@PathVariable String user_id) {
        try {
            int result = notificationService.noOfNotificationPending(user_id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            response.put("data", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notificationListForSupport")
    public ResponseEntity<Map<String, Object>> notificationListForSupport() {
        try {

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            response.put("data", notificationService.callNotificationListForSupport());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateSupportNotification/{user_id}")
    public ResponseEntity<Map<String, Object>> UpdateSupportNotification(@PathVariable String user_id, @RequestParam String id) {
        try {
            notificationService.updateSupportNotifications(user_id, id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}


