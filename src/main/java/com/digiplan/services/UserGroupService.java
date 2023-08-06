package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.UserGroup;

public interface UserGroupService {

    UserGroup getUserGroup(String groupId);

    List<UserGroup> getAllUserGroups();

    UserGroup addUserGroup(UserGroup userGroupData);

    UserGroup updateUserGroup(String groupId, UserGroup userGroupData);

    String deleteUserGroup(String groupId);
}
