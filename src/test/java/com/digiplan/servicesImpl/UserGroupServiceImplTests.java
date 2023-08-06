package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.UserGroup;
import com.digiplan.repositories.UserGroupRepository;

@SpringBootTest
public class UserGroupServiceImplTests {

    @InjectMocks
    private UserGroupServiceImpl userGroupServiceImpl;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Test
    public void test_getUserGroup() {
        UserGroup userGroup = new UserGroup("1", "CATCAM");
        Optional<UserGroup> retrievedData = Optional.of(new UserGroup("1", "Testing"));
        String groupId = "1";
        when(userGroupRepository.findById(groupId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(userGroupRepository.getById(groupId)).thenReturn(userGroup);
        assertEquals(groupId, userGroupServiceImpl.getUserGroup(groupId).getGroupId());
    }

    @Test
    public void test_getAllUserGroups() {
        List<UserGroup> userGroup = new ArrayList<>();
        userGroup.add(new UserGroup("1", "CATCAM"));
        userGroup.add(new UserGroup("2", "CATCAM"));
        userGroup.add(new UserGroup("3", "CATCAM"));
        when(userGroupRepository.findAll()).thenReturn(userGroup);
        assertEquals(3, userGroupServiceImpl.getAllUserGroups().size());
    }

    @Test
    public void test_addUserGroup() {
        UserGroup userGroup = new UserGroup("1", "CATCAM");
        when(userGroupRepository.saveAndFlush(userGroup)).thenReturn(userGroup);
        assertEquals(userGroup, userGroupServiceImpl.addUserGroup(userGroup));
    }

    @Test
    public void test_updateUserGroup() {
        UserGroup userGroup = new UserGroup("1", "CATCAM");
        Optional<UserGroup> retrievedData = Optional.of(new UserGroup("1", "Testing"));
        String groupId = "1";
        when(userGroupRepository.findById(groupId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(userGroupRepository.saveAndFlush(userGroup)).thenReturn(userGroup);
        assertEquals(userGroup, userGroupServiceImpl.updateUserGroup(groupId, userGroup));
    }

    @Test
    public void test_deleteUserGroup() {
        String groupId = "1";
        userGroupServiceImpl.deleteUserGroup(groupId);
        verify(userGroupRepository, times(1)).deleteById(groupId);
    }

}
