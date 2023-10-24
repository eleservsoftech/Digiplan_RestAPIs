package com.digiplan.servicesImpl;

import com.digiplan.entities.Demo;
import com.digiplan.repositories.DemoRepository;
import com.digiplan.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public ResponseEntity<Map> getAllDemoUsers() {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            List list = demoRepository.findAll();
            if (list != null) {
                map.put("status", 200);
                map.put("message", "Data Present");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "No Data Found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getAllDemoUsers Exception : " + exception);
            map.put("status", 500);
            map.put("message", "Internal Server Errror");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getDemoUser(int id) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            Optional<Demo> demo = demoRepository.findById(id);
            if (demo.isPresent()) {
                map.put("status", 200);
                map.put("message", "Data Present");
                map.put("data", demo.get());
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "No Data Found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getDemoUser Exception : " + exception);
            map.put("status", 500);
            map.put("message", "Internal Server Errror");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> addDemoUser(Demo demo) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            Demo demoData = demoRepository.saveAndFlush(demo);
            map.put("status", 200);
            map.put("message", "Data Saved");
            map.put("data", demoData);
            status = HttpStatus.OK;
        } catch (Exception exception) {
            System.out.println("@addDemoUser Exception : " + exception);
            map.put("status", 500);
            map.put("message", "Internal Server Errror");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> updateDemoUser(Demo demo) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            int response = demoRepository.updateUser(demo.getName(), demo.getEmail(), demo.getNumber(), demo.getId());
            if (response != 0) {
                Demo demoData = demoRepository.findById(demo.getId()).get();
                map.put("status", 200);
                map.put("message", "Data Updated");
                map.put("data", demoData);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Id not found");
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@updateDemoUser Exception : " + exception);
            map.put("status", 500);
            map.put("message", "Internal Server Errror");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deleteDemoUser(int id) {
        Map map = new HashMap();
        HttpStatus status = null;
        try {
            Optional<Demo> demo = demoRepository.findById(id);
            if (demo.isPresent()) {
                demoRepository.deleteById(id);
                map.put("status", 200);
                map.put("message", "Data Deleted");
            } else {
                map.put("status", 404);
                map.put("message", "Data Not Present");
            }
            status = HttpStatus.OK;
        } catch (Exception exception) {
            System.out.println("@deleteDemoUser Exception : " + exception);
            map.put("status", 500);
            map.put("message", "Internal Server Errror");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }
}
