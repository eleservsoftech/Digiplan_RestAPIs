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

    public ResponseEntity<Map> GetAlignerDispatchData(String dispatchedId);

    public ResponseEntity<Map> updateAlignerSchedule(String case_id,String dispatchedId,String aligner_no_u,String aligner_no_l,String actualDate,String remarks,String user);


}
