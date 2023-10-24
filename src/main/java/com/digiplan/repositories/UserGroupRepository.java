package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, String> {

}
