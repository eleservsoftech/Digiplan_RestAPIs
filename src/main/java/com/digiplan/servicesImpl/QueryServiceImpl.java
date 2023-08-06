package com.digiplan.servicesImpl;

import com.digiplan.entities.Logger;
import com.digiplan.entities.Query;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.QueryRepository;
import com.digiplan.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Query getQuery(String queryId) {
        Query query = null;
        try {
            Optional<Query> check = queryRepository.findById(queryId);
            if (check.isPresent())
                query = queryRepository.getById(queryId);
        } catch (Exception exception) {
            System.out.println("@getQuery Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getQuery", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return query;
    }

    @Override
    public List<Query> getAllQueries() {
        List<Query> queriesList = null;
        try {
            queriesList = queryRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllQueries Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllQueries", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return queriesList;
    }

    @Override
    public ResponseEntity<Map> addQuery(Query queryData) {
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            queryRepository.saveAndFlush(queryData);
            map.put("status", 201);
            map.put("message", "Contact form has been submitted!");
            status = HttpStatus.CREATED;
        } catch (Exception exception) {
            System.out.println("@addQuery Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addQuery", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public Query updateQuery(String queryId, Query queryData) {
        Query query = null;
        try {
            Optional<Query> check = queryRepository.findById(queryId);
            if (check.isPresent())
                query = queryRepository.saveAndFlush(queryData);
        } catch (Exception exception) {
            System.out.println("@updateQuery Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateQuery", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return query;
    }

    @Override
    public String deleteQuery(String queryId) {
        String status = "";
        try {
            queryRepository.deleteById(queryId);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteQuery Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteQuery", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
