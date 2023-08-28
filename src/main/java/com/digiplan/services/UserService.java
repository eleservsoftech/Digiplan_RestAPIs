package com.digiplan.services;

import com.digiplan.entities.User;
import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUser(Integer id);

    List<User> getAllUsers();

   // User addUser(User userData);
   public ResponseEntity<Map> addUser(User addUser);
    User updateUser(Integer id, User userData);

    String deleteUser(Integer id);

    ResponseEntity<Map> login(User userData);

    ResponseEntity<Map> sendOTP(User userData);

    ResponseEntity<Map> forgotPassword(String username, String password, Integer otp);

    ResponseEntity<Map> changePassword(User userData);

    //For Receipt Application For @Tarun
    JSONArray getAllProviders();

    ResponseEntity<Map> getDoctorsList();

    public ResponseEntity<Map> doctorCases(String email);

    public ResponseEntity<Map> getUserMobileList(Long phonenumber);

    public ResponseEntity<Map> getUserByEmail(String email);

}
