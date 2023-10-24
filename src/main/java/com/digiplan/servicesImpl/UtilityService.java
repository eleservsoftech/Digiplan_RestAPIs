package com.digiplan.servicesImpl;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilityService {

    public String getLoggerCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
