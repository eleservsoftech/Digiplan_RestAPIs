package com.digiplan.controllers;

import com.digiplan.entities.UserGroup;
import com.digiplan.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/getUserGroup/{groupId}")
    public Map<String,Object> getUserGroup(@PathVariable String groupId) {
        Map<String, Object> map=new HashMap<>();
        try{

            if(this.userGroupService.getUserGroup(groupId)!=null){
                map.put("status_code", "200");
                map.put("results", this.userGroupService.getUserGroup(groupId));
                map.put("message","Success");
            }
            else{
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message","Records Not Found or Group Id is Invalid! ");
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message" ,e.getMessage());
        }
            return  map;

    }

    @GetMapping("/getAllUserGroups")
    public Map<String,Object> getAllUserGroups() {
       Map<String,Object> map =new HashMap<>();
       try {
           if (this.userGroupService.getAllUserGroups() != null) {
               map.put("status_code", "200");
               map.put("results", this.userGroupService.getAllUserGroups());
               map.put("message", "Success");
           } else {
               map.put("status_code", "204");
               map.put("results", "No Content!");
               map.put("message", "Records Not Found!");
           }
       }catch (Exception e){
           map.put("status_code", "500");
           map.put("results", "Internal Server Error!");
           map.put("message" ,e.getMessage());
       }
       return map;
    }
    @PostMapping("/addUserGroup")
    public ResponseEntity<Map> addState(@RequestBody  UserGroup userGroupData) {
        return this.userGroupService.addUserGroup(userGroupData);
    }

    @PutMapping("/updateUserGroup/{groupId}")
    public ResponseEntity<UserGroup> updateUserGroup(@PathVariable String groupId,
                                                     @RequestBody UserGroup userGroupData) {
        UserGroup userGroup = this.userGroupService.updateUserGroup(groupId, userGroupData);
        if (userGroup != null)
            return new ResponseEntity<UserGroup>(userGroup, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUserGroup/{groupId}")
    public ResponseEntity<String> deleteUserGroup(@PathVariable String groupId) {
        String status = this.userGroupService.deleteUserGroup(groupId);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
