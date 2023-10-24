package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.UserGroup;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.UserGroupRepository;
import com.digiplan.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public UserGroup getUserGroup(String groupId) {
        UserGroup userGroup = null;
        try {
            Optional<UserGroup> check = userGroupRepository.findById(groupId);
            if (check.isPresent())
                userGroup = userGroupRepository.getById(groupId);
        } catch (Exception exception) {
            System.out.println("@getUserGroup Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserGroup", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return userGroup;
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        List<UserGroup> userGroupsList = null;
        try {
            userGroupsList = userGroupRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllUserGroups Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllUserGroups", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return userGroupsList;
    }

//    @Override
//    public UserGroup addUserGroup(UserGroup userGroupData) {
//        UserGroup userGroup = null;
//        try {
//            if(!userGroupData.getGroupId().isEmpty() && !userGroup.getGroupName().isEmpty()){
//            userGroup = userGroupRepository.saveAndFlush(userGroupData);
//            }else{
//
//            }
//        } catch (Exception exception) {
//            System.out.println("@addUserGroup Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addUserGroup", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//        }
//        return userGroup;
//    }
@Override
public ResponseEntity<Map> addUserGroup(UserGroup addUserGroup) {
    Map<Object, Object> map = new HashMap<>();
    HttpStatus status = null;
    try {

        if(!addUserGroup.getGroupId().isEmpty() && !addUserGroup.getGroupName().isEmpty())
        {
            UserGroup  UserGroupObj = userGroupRepository.save(addUserGroup);
            map.put("status_code", "201");
            map.put("message", "Data saved successfully");
            map.put("data", UserGroupObj);
            status = HttpStatus.CREATED;
        }else {
            map.put("status_code", "400");
            map.put("message", "Either Group id is blank or Group name!");
            map.put("data", "Data not saved");
            status = HttpStatus.BAD_REQUEST;
        }
    } catch (Exception ex) {
        map.put("status_code", "500");
        map.put("message", ex.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity(map, status);
}

    @Override
    public UserGroup updateUserGroup(String groupId, UserGroup userGroupData) {
        UserGroup userGroup = null;
        try {
            Optional<UserGroup> check = userGroupRepository.findById(groupId);
            if (check.isPresent())
                userGroup = userGroupRepository.saveAndFlush(userGroupData);
        } catch (Exception exception) {
            System.out.println("@updateUserGroup Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateUserGroup", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return userGroup;
    }

    @Override
    public String deleteUserGroup(String groupId) {
        String status = "";
        try {
            userGroupRepository.deleteById(groupId);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteUserGroup Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteUserGroup", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
