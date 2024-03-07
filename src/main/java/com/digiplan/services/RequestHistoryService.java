package com.digiplan.services;

import com.digiplan.entities.RequestHistory;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RequestHistoryService {

    ResponseEntity<Map> createRequestHistory(RequestHistory requestHistory);

    RequestHistory getRequestHistoryById(Long id);
}
