package com.digiplan.services;

import com.digiplan.entities.AlignerWearingScheduleEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AlignerWearingScheduleService {

    public ResponseEntity<Map> addAlignerWearingSchedule(AlignerWearingScheduleEntity addAlignerWearingSchedule) ;

    public ResponseEntity<Map> updateAlignerWearingSchedule(Integer id, AlignerWearingScheduleEntity updateAlignerWearingSchedule);

    public ResponseEntity<Map> getAlignerWearingSchedule(Integer id);

    public ResponseEntity<Map> getAlignerWearingSchedules();

    public ResponseEntity<Map> deleteAlignerWearingSchedule(Integer id);


}
