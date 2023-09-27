package com.digiplan.servicesImpl;

import com.digiplan.entities.AlignerWearingScheduleEntity;
import com.digiplan.repositories.AlignerWearingScheduleRepository;
import com.digiplan.services.AlignerWearingScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AlignerWearingScheduleServiceImpl  implements AlignerWearingScheduleService {

    @Autowired
    private AlignerWearingScheduleRepository alignerWearingScheduleRepo;
    @Override
    public ResponseEntity<Map> addAlignerWearingSchedule(AlignerWearingScheduleEntity addAlignerWearingSchedule) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            AlignerWearingScheduleEntity AlignerWearingScheduleEntityEntityObj = alignerWearingScheduleRepo.saveAndFlush(addAlignerWearingSchedule);
            map.put("status_code", HttpStatus.CREATED.value());
            map.put("message", "Data saved successfully");
            map.put("data", AlignerWearingScheduleEntityEntityObj);
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addAlignerWearingScheduleEntityEntity{0} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    @Override
    public ResponseEntity<Map> updateAlignerWearingSchedule(Integer id, AlignerWearingScheduleEntity updateAlignerWearingSchedule) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<AlignerWearingScheduleEntity> isExist = alignerWearingScheduleRepo.findById(id);
            if (isExist.isPresent()) {
                AlignerWearingScheduleEntity alignerWearingScheduleObj = alignerWearingScheduleRepo.saveAndFlush(updateAlignerWearingSchedule);
                map.put("status_code", HttpStatus.OK.value());
                map.put("message", "Data updated successfully");
                map.put("data", alignerWearingScheduleObj);
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", updateAlignerWearingSchedule);
                map.put("message", "data not updated!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  updateAlignerWearingScheduleEntity{0} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getAlignerWearingSchedule(Integer id) {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (this.alignerWearingScheduleRepo.findById(id).isPresent()) {
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", this.alignerWearingScheduleRepo.findById(id));
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", this.alignerWearingScheduleRepo.findById(id));
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  getAlignerWearingSchedule{0} " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getAlignerWearingSchedules() {
        HttpStatus status = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (!this.alignerWearingScheduleRepo.findAll().isEmpty()) {
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", this.alignerWearingScheduleRepo.findAll());
                map.put("message", "Data Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", this.alignerWearingScheduleRepo.findAll());
                map.put("error", "Data Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log getAlignerWearingScheduleEntitys{0} " + e.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deleteAlignerWearingSchedule(Integer id) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
            if (this.alignerWearingScheduleRepo.findById(id).isPresent()) {
                this.alignerWearingScheduleRepo.deleteById(id);
                map.put("status_code", HttpStatus.OK.value());
                map.put("results", id);
                map.put("message", "Deleted");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", HttpStatus.NOT_FOUND.value());
                map.put("results", id);
                map.put("error", "Not Deleted!");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  deleteAlignerWearingScheduleEntity{0} " + ex.getMessage());
        }
        return new ResponseEntity<>(map, status);
    }
}
