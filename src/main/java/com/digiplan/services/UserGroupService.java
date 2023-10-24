package com.digiplan.services;

import java.util.List;
import java.util.Map;

import com.digiplan.entities.UserGroup;
import org.springframework.http.ResponseEntity;

public interface UserGroupService {

    UserGroup getUserGroup(String groupId);

    List<UserGroup> getAllUserGroups();

   //UserGroup addUserGroup(UserGroup userGroupData);
   public ResponseEntity<Map> addUserGroup(UserGroup addUserGroup);

    UserGroup updateUserGroup(String groupId, UserGroup userGroupData);

    String deleteUserGroup(String groupId);
}
