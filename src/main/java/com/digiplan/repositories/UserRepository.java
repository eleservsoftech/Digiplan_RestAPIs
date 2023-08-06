package com.digiplan.repositories;

import com.digiplan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsernameAndPhoneNumber(String username, Long phoneNumber);

    User findByUsername(String username);

    @Query(value = "select * from alignwise_users where groupid=(select groupid from alignwise_users where username=:username and typeofuser='DoctorAdmin')", nativeQuery = true)
    List<User> findAllUsersList(@Param("username") String username);

    @Query(value = "select * from alignwise_users where typeofuser='Doctor'", nativeQuery = true)
    List<User> getDoctorsList();

    @Query(value = "select * from alignwise_users limit 50", nativeQuery = true)
    List<User> getDoctorsListByLimit();

    @Query(value = "select * from alignwise_users where firstname like %:searchDoctor% or lastname like %:searchDoctor% limit 50", nativeQuery = true)
    List<User> getDoctorsListByDoctorname(@Param("searchDoctor") String searchDoctor);
}
