package com.digiplan.servicesImpl;

import com.digiplan.entities.Draft;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.DraftRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DraftServiceImpl implements DraftService {

    @Autowired
    private DraftRepository draftRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Draft getDraft(Integer id) {
        Draft draft = null;
        try {
            Optional<Draft> check = draftRepository.findById(id);
            if (check.isPresent())
                draft = draftRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

    @Override
    public List<Draft> getAllDrafts() {
        List<Draft> draftsList = null;
        try {
            draftsList = draftRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllDrafts Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllDrafts", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draftsList;
    }

    @Override
    public Draft addDraft(Draft draftData) {
        Draft draft = null;
        try {
            draft = draftRepository.saveAndFlush(draftData);
        } catch (Exception exception) {
            System.out.println("@addDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

    @Override
    public Draft updateDraft(Integer id, Draft draftData) {
        Draft draft = null;
        try {
            Optional<Draft> check = draftRepository.findById(id);
            if (check.isPresent())
                draft = draftRepository.saveAndFlush(draftData);
        } catch (Exception exception) {
            System.out.println("@updateDraft Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return draft;
    }

   /* @Override
    public String deleteDraft(Integer id) {
        log.info("@Start deleteDraft");
        String status = "";
        try {
            draftRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            log.error("Exception = " + exception);
        }
        return status;
    } */

    @Override
    public ResponseEntity<Map> viewDrafts(Draft draftData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            List<Draft> drafts = new ArrayList<>();
            List<Draft> draftList = draftRepository.findAll();
            for (Draft draft : draftList) {
                if (draft.getSavedBy().equalsIgnoreCase(draftData.getSavedBy()) && draft.getIsActive() == 1) {
                    drafts.add(draft);
                }
            }
            if (drafts.isEmpty()) {
                map.put("status", 404);
                map.put("message", "No Record Found!");
                map.put("response", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "Record Found!");
                map.put("response", drafts);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@viewDrafts Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "viewDrafts", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> deleteDraft(String draftId) {
        HttpStatus status = null;
        Map<String, Object> map = new HashMap();
        try {
            Optional<Draft> check = draftRepository.findById(Integer.parseInt(draftId));
            if (check.isPresent()) {
                Draft draft = draftRepository.findById(Integer.parseInt(draftId)).get();
                if (draft.getIsActive() == 1) {
                    draft.setIsActive(0);
                    draftRepository.saveAndFlush(draft);
                    map.put("message", "Record Deleted");
                    map.put("status", 200);
                    status = HttpStatus.OK;
                } else {
                    map.put("message", "Record Not Found");
                    map.put("status", 404);
                    status = HttpStatus.NOT_FOUND;
                }
            } else {
                map.put("message", "Record Not Found");
                map.put("status", 404);
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@deleteDraft Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteDraft", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("message", "Internal Server Error");
            map.put("status", 500);
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

}
