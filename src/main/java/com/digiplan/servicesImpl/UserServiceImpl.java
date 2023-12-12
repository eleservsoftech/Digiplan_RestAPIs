package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.MyDoctorCases;
import com.digiplan.entities.User;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.UserRepository;
import com.digiplan.services.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    int otp = 12345;

    @Override
    public User getUser(Integer id) {
        User user = null;
        try {
            Optional<User> check = userRepository.findById(id);
            if (check.isPresent())
                user = userRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getUser Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUser", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = null;
        try {
            usersList = userRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllUsers Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllUsers", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return usersList;
    }

    // vikas code
//@Override
//public ResponseEntity<Map> addUser(User addUser) {
//    Map<Object, Object> map = new HashMap<>();
//    HttpStatus status = null;
//    User  userObj = null;
//    try {
//        if(!addUser.getUsername().isEmpty() &&  !addUser.getPassword().isEmpty()) {
//            userObj = userRepository.save(addUser);
//            map.put("status_code", "201");
//            map.put("message", "Data saved successfully");
//            map.put("data", userObj);
//            status = HttpStatus.CREATED;
//        }else{
//            map.put("status_code", "400");
//            map.put("errorMessage", "blank User name and password not accepted!");
//            status = HttpStatus.BAD_REQUEST;
//        }
//
//    } catch (Exception ex) {
//        map.put("status_code", "500");
//        map.put("message", ex.getMessage());
//        status = HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//    return new ResponseEntity(map, status);
//}
    // vikas code

    //AMAN TESTING
//    @Override
//    public ResponseEntity<Map> addUser(User addUser) {
//        Map<Object, Object> map = new HashMap<>();
//        HttpStatus status = null;
//        User userObj = null;
//        try {
//            // Check if the mobile number is already registered
//            long existingUserCountByMobile = userRepository.countUsersByPhoneNumber(addUser.getPhoneNumber());
//            if (existingUserCountByMobile > 0 && (addUser.getId() == null || existingUserCountByMobile > 1)) {
//                map.put("status_code", "400");
//                map.put("errorMessage", "Mobile number already registered!");
//                status = HttpStatus.BAD_REQUEST;
//                return new ResponseEntity<>(map, status);
//            }
//
//            // Check if the email is already registered
//            long existingUserCountByEmail = userRepository.countUsersByEmail(addUser.getEmail());
//            if (existingUserCountByEmail > 0 && (addUser.getId() == null || existingUserCountByEmail > 1)) {
//                map.put("status_code", "400");
//                map.put("errorMessage", "Email already registered!");
//                status = HttpStatus.BAD_REQUEST;
//                return new ResponseEntity<>(map, status);
//            }
//
//            // Continue with the user registration
//            if (!addUser.getUsername().isEmpty() && !addUser.getPassword().isEmpty()) {
//                userObj = userRepository.save(addUser);
//                map.put("status_code", "201");
//                map.put("message", "Data saved successfully");
//                map.put("data", userObj);
//                status = HttpStatus.CREATED;
//            } else {
//                map.put("status_code", "400");
//                map.put("errorMessage", "Blank User name and password not accepted!");
//                status = HttpStatus.BAD_REQUEST;
//            }
//
//        } catch (Exception ex) {
//            map.put("status_code", "500");
//            map.put("message", ex.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(map, status);
//    }
    //AMAN TESTING


    //aman 08-12-23
    @Override
    public ResponseEntity<Map> addUser(User addUser) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status;

        try {
            // Check if the mobile number is already registered
            long existingUserCountByMobile = userRepository.countUsersByPhoneNumber(addUser.getPhoneNumber());
            if (existingUserCountByMobile > 0 && (addUser.getId() == null || existingUserCountByMobile > 1)) {
                map.put("status_code", "400");
                map.put("errorMessage", "Mobile number already registered!");
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(map, status);
            }

            // Check if the email is already registered
            long existingUserCountByEmail = userRepository.countUsersByEmail(addUser.getEmail());
            if (existingUserCountByEmail > 0 && (addUser.getId() == null || existingUserCountByEmail > 1)) {
                map.put("status_code", "400");
                map.put("errorMessage", "Email already registered!");
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(map, status);
            }

            // Generate a unique alphanumeric string for doctor_unique_no if not present
            if (addUser.getDoctor_unique_no() == null || addUser.getDoctor_unique_no().isEmpty()) {
                String uniqueString = generateUniqueString();
                addUser.setDoctor_unique_no(uniqueString);
            }

            // Continue with the user registration
            if (!addUser.getUsername().isEmpty() && !addUser.getPassword().isEmpty()) {
                User userObj = userRepository.save(addUser);
                map.put("status_code", "201");
                map.put("message", "Data saved successfully");
                map.put("data", userObj);
                status = HttpStatus.CREATED;
            } else {
                map.put("status_code", "400");
                map.put("errorMessage", "Blank User name and password not accepted!");
                status = HttpStatus.BAD_REQUEST;
            }

        } catch (Exception ex) {
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    private String generateUniqueString() {
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        String hexString = Long.toHexString(leastSignificantBits);
        hexString = hexString.substring(hexString.length() - 6);

        char[] hexChars = hexString.toCharArray();
        for (int i = 0; i < hexChars.length; i++) {
            if (Character.isDigit(hexChars[i])) {
                hexChars[i] = (char) ('A' + Integer.parseInt(String.valueOf(hexChars[i])));
            }
        }

        return new String(hexChars);
    }

//    aman 8-12-23


    @Override
    public User updateUser(Integer id, User userData) {
        User user = null;
        try {
            Optional<User> check = userRepository.findById(id);
            if (check.isPresent())
                user = userRepository.saveAndFlush(userData);
        } catch (Exception exception) {
            System.out.println("@updateUser Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateUser", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return user;
    }

    @Override
    public String deleteUser(Integer id) {
        String status = "";
        try {
            userRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteUser Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteUser", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

    @Override
    public ResponseEntity<Map> login(User userData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            User user = userRepository.findByUsernameAndPassword(userData.getUsername(), userData.getPassword());
            if(user.getIsdisable()==0){
                if (user != null && user.getPassword().equals(userData.getPassword())) {
                    map.put("status", 200);
                    map.put("message", "Logged In Successfully!");
                    map.put("response", user);
                    status = HttpStatus.OK;
                } else {
                    map.put("status", 404);
                    map.put("message", "Invalid Credentials!");
                    map.put("response", "");
                    status = HttpStatus.NOT_FOUND;
                }
            }else{
                map.put("status", 401);
                map.put("message", "UnAuthorized");
                map.put("response", "");
                status = HttpStatus.UNAUTHORIZED;
            }


        } catch (Exception exception) {
            System.out.println("@login Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "login", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> sendOTP(User userData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            User user = userRepository.findByUsernameAndPhoneNumber(userData.getUsername(), userData.getPhoneNumber());
            if (user != null) {
                map.put("status", 200);
                map.put("message", "OTP sent successfully");
                map.put("otp", otp);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Invalid User");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@forgetPassword Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "forgetPassword", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> forgotPassword(String username, String password, Integer otp) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            if (this.otp == otp) {
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    if (!user.getPassword().equals(password)) {
                        user.setPassword(password);
                        userRepository.saveAndFlush(user);
                    }
                    map.put("status", 200);
                    map.put("message", "Reset Successful");
                    status = HttpStatus.OK;
                } else {
                    map.put("status", 404);
                    map.put("message", "Invalid User");
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("status", 500);
                map.put("message", "Invalid OTP");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception exception) {
            System.out.println("@forgetPassword Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "forgetPassword", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> changePassword(User userData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            if (userData.getConfirmPassword().equals(userData.getNewPassword())) {
                User user = userRepository.findByUsernameAndPassword(userData.getUsername(), userData.getPassword());
                if (user != null) {
                    if (!user.getPassword().equals(userData.getNewPassword())) {
                        user.setPassword(userData.getNewPassword());
                        userRepository.saveAndFlush(user);
                    }
                    map.put("status", 200);
                    map.put("message", "Reset Successful");
                    status = HttpStatus.OK;
                } else {
                    map.put("status", 401);
                    map.put("message", "Old password is not valid");
                    status = HttpStatus.UNAUTHORIZED;
                }
            } else {
                map.put("status", 500);
                map.put("message", "The password confirmation does not match");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception exception) {
            System.out.println("@changePassword Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "changePassword", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(map, status);
    }

    //For Receipt Application For @Tarun Map Details
    @Override
    public JSONArray getAllProviders() {
        JSONArray jsonArray = new JSONArray();
        try {
            List<User> userList = userRepository.findAll();
            for (User user : userList) {
                if (user.getTtWattsProvider().equals("Yes") && (user.getLongitude() != "" || user.getLongitude() != null) && (user.getLatitude() != "" || user.getLatitude() != null)) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("doctorName", user.getFirstname() + " " + user.getLastname());
                    jsonObject.put("phoneNumber", user.getPhoneNumber());
                    jsonObject.put("email", user.getEmail());
                    jsonObject.put("clinicName", user.getClinicName());
                    jsonObject.put("address", user.getAddress());
                    jsonObject.put("longitude", user.getLongitude());
                    jsonObject.put("latitude", user.getLatitude());
                    jsonObject.put("pin", user.getPin());
                    jsonObject.put("city", user.getCity());
                    jsonArray.add(jsonObject);
                }
            }
        } catch (Exception exception) {
            System.out.println("@getAllProviders Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllProviders", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return jsonArray;
    }

    @Override
    public ResponseEntity<Map> getDoctorsList() {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<User> userList = userRepository.getDoctorsList();
            if (!userList.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", userList);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            System.out.println("@getDoctorsList Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDoctorsList", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    public ResponseEntity<Map> doctorCases(String email) {
        Map map = new HashMap();
        HttpStatus status = null;
        List<MyDoctorCases> list = new ArrayList<>();

        try {
            list = userRepository.getDoctorCases(email);
            if (!list.isEmpty()) {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Data not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }


    //
    public ResponseEntity<Map> getUserMobileList(Long phonenumber) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<User> userList = userRepository.getDoctorsByMobileList(phonenumber);
            if (!userList.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
                map.put("data", userList);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            System.out.println("@getDoctorsList Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserMobileList{}", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    public ResponseEntity<Map> getUserByEmail(String email) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<User> userEmail = userRepository.getUserByEmailId(email);
            if (!userEmail.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
                map.put("data", userEmail);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserByEmail{}", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getNewSignUp(String date) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<User> userEmail = userRepository.getNewSignUp(date);
            if (!userEmail.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
                map.put("data", userEmail);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserByEmail{}", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> doctorDisable(String username) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            int result = userRepository.doctorDisable(username);
            if (result==1) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserByEmail{}", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> doctorEnable(String username) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            int result = userRepository.doctorEnable(username);
            if (result==1) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getUserByEmail{}", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }
}
