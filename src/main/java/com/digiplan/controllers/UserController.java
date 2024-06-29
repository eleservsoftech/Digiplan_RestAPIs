package com.digiplan.controllers;

import com.digiplan.entities.User;
import com.digiplan.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = this.userService.getUser(id);
        if (user != null)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllUsers")
    public Map<String,Object> getAllUsers() {

        Map<String, Object> map = new HashMap<>();
        try {

            if (this.userService.getAllUsers().size() > 0) {
                map.put("status_code", "200");
                map.put("results", this.userService.getAllUsers());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        } catch (Exception e) {
            map.put("status_code", "204");
            map.put("results", "No Content!");
            map.put("message", "Records Not Found!");
        }
        return map;
    }


    @PostMapping("/addUser")
    public ResponseEntity<Map> createUser(@RequestBody User userData) {
        return this.userService.addUser(userData);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userData) {
        User user = this.userService.updateUser(id, userData);
        if (user != null)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        String status = this.userService.deleteUser(id);
        if (status.equals("Deleted"))
            return new ResponseEntity<String>(status, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody User userData) {
        return this.userService.login(userData);
    }

    @PostMapping("/sendOTP")
    public ResponseEntity<Map> sendOTP(@RequestBody User userData) {
        return this.userService.sendOTP(userData);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Map> forgotPassword(@RequestParam String username, @RequestParam String password, @RequestParam Integer otp) {
        System.out.println("OTP : "+otp);
        return this.userService.forgotPassword(username, password, otp);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Map> changePassword(@RequestBody User userData) {
        return this.userService.changePassword(userData);
    }

    //For Receipt Application For @Tarun
    @PostMapping(value = "/providers")
    public JSONArray getAllProviders() {
        return userService.getAllProviders();
    }

    @GetMapping("/getDoctorsList")
    public ResponseEntity<Map> getDoctorsList(){
        return userService.getDoctorsList();
    }

    @GetMapping("/getDoctorActiveCases")
    public ResponseEntity<Map> getDoctorCases(@RequestParam String email) {
        return userService.doctorCases(email);
    }
    @GetMapping("/getUserByMobileNumber/{phoneNumber}")
    public ResponseEntity<Map> getUserByMobileNumber(@PathVariable Long phoneNumber){
        return userService.getUserMobileList(phoneNumber);
    }
    @GetMapping("/GetUserByEmailID/{email}")
    public ResponseEntity<Map> getUserByEmailId(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/GetNewSignUp/{date}")
    public ResponseEntity<Map> getNewSignUp(@PathVariable String date){
        return userService.getNewSignUp(date);
    }

    @PutMapping("/DisableDoctor/{username}")
    public ResponseEntity<Map> isDisableDoctor(@PathVariable String username){
        return userService.doctorDisable(username);
    }

    @PutMapping("/EnableDoctor/{username}")
    public ResponseEntity<Map> isEnableDoctor(@PathVariable String username){
        return userService.doctorEnable(username);
    }

}
