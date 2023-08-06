package com.digiplan.servicesImpl;

import com.digiplan.entities.Doctor;
import com.digiplan.entities.Logger;
import com.digiplan.entities.User;
import com.digiplan.repositories.DoctorRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.UserRepository;
import com.digiplan.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Doctor getDoctor(String caseId) {
        Doctor doctor = null;
        try {
            Optional<Doctor> check = doctorRepository.findById(caseId);
            if (check.isPresent())
                doctor = doctorRepository.getById(caseId);
        } catch (Exception exception) {
            System.out.println("@getDoctor Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDoctor", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return doctor;
    }

    @Override
    public ResponseEntity<Map> getAllDoctors(String searchDoctor) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            List<User> userList = new ArrayList<>();
            if (searchDoctor.equals("") || searchDoctor.equals(null))
                userList = userRepository.getDoctorsListByLimit();
            else
                userList = userRepository.getDoctorsListByDoctorname(searchDoctor);

            if (userList != null) {
                map.put("status", 200);
                map.put("messgae", "OK");
                map.put("data", userList);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("messgae", "No Data Present");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getAllDoctors Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllDoctors", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("messgae", "Internel Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public Doctor addDoctor(Doctor doctorData) {
        Doctor doctor = null;
        try {
            doctor = doctorRepository.saveAndFlush(doctorData);
        } catch (Exception exception) {
            System.out.println("@addDoctor Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addDoctor", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return doctor;
    }

    @Override
    public Doctor updateDoctor(String caseId, Doctor doctorData) {
        Doctor doctor = null;
        try {
            Optional<Doctor> check = doctorRepository.findById(caseId);
            if (check.isPresent())
                doctor = doctorRepository.saveAndFlush(doctorData);
        } catch (Exception exception) {
            System.out.println("@updateDoctor Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateDoctor", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return doctor;
    }

    @Override
    public String deleteDoctor(String caseId) {
        String status = "";
        try {
            doctorRepository.deleteById(caseId);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteDoctor Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteDoctor", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
