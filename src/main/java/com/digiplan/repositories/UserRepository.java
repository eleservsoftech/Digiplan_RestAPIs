package com.digiplan.repositories;

import com.digiplan.entities.MyDoctorCases;
import com.digiplan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsernameAndPhoneNumber(String username, Long phoneNumber);

    User findByUsername(String username);

    //@Query(value = "select * from alignwise_users where groupid=(select groupid from alignwise_users where username=:username and typeofuser='DoctorAdmin')", nativeQuery = true)
    @Query(value = "select * from alignwise_users where groupid=(select groupid from alignwise_users where username=:username)", nativeQuery = true)
    List<User> findAllUsersList(@Param("username") String username);

    @Query(value = "select * from alignwise_users where typeofuser='Doctor'", nativeQuery = true)
    List<User> getDoctorsList();

    @Query(value = "select * from alignwise_users limit 50", nativeQuery = true)
    List<User> getDoctorsListByLimit();

    @Query(value = "select * from alignwise_users where firstname like %:searchDoctor% or lastname like %:searchDoctor% limit 50", nativeQuery = true)
    List<User> getDoctorsListByDoctorname(@Param("searchDoctor") String searchDoctor);

    @Query(value = "call GetMyCase(?)", nativeQuery = true)
    List<MyDoctorCases> getDoctorCases(String email);

    // getting user by mobile number
    @Query(value = "select * from alignwise_users where phoneNumber=:phoneNumber ", nativeQuery = true)
    List<User> getDoctorsByMobileList(@Param("phoneNumber")Long phoneNumber);

    // get user data by Email address
    @Query(value = "select * from alignwise_users where email=:email ", nativeQuery = true)

    List<User> getUserByEmailId(@Param("email") String email);
    User findByEmail(String email);
    User findByPhoneNumber(Long phoneNumber);

    long countUsersByPhoneNumber(Long phoneNumber);
    long countUsersByEmail(String email);
}
