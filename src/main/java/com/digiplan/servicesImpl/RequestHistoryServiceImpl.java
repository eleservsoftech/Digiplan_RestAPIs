package com.digiplan.servicesImpl;

import com.digiplan.entities.RequestHistory;
import com.digiplan.repositories.RequestHistoryRepository;
import com.digiplan.services.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestHistoryServiceImpl implements RequestHistoryService {

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    @Override
    public ResponseEntity<Map> createRequestHistory(RequestHistory requestHistory) {
        RequestHistory savedRequestHistory = requestHistoryRepository.save(requestHistory);

        Map<String, Object> response = new HashMap<>();
        response.put("Id", savedRequestHistory.getId());
        response.put("message", "Request history created successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    public RequestHistory getRequestHistoryById(Long id) {
        Optional<RequestHistory> requestHistoryOptional = requestHistoryRepository.findById(id);
        return requestHistoryOptional.orElse(null);
    }


}
