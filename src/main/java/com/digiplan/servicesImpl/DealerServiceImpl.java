package com.digiplan.servicesImpl;

import com.digiplan.entities.Dealer;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.DealerRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Dealer getDealer(Integer id) {
        Dealer dealer = null;
        try {
            Optional<Dealer> check = dealerRepository.findById(id);
            if (check.isPresent())
                dealer = dealerRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getDealer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getDealer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return dealer;
    }

    @Override
    public List<Dealer> getAllDealers() {
        List<Dealer> dealersList = null;
        try {
            dealersList = dealerRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllDealers Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllDealers", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return dealersList;
    }

    @Override
    public Dealer addDealer(Dealer dealerData) {
        Dealer dealer = null;
        try {
            dealer = dealerRepository.saveAndFlush(dealerData);
        } catch (Exception exception) {
            System.out.println("@addDealer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addDealer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return dealer;
    }

    @Override
    public Dealer updateDealer(Integer id, Dealer dealerData) {
        Dealer dealer = null;
        try {
            Optional<Dealer> check = dealerRepository.findById(id);
            if (check.isPresent())
                dealer = dealerRepository.saveAndFlush(dealerData);
        } catch (Exception exception) {
            System.out.println("@updateDealer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateDealer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return dealer;
    }

    @Override
    public String deleteDealer(Integer id) {
        String status = "";
        try {
            dealerRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteDealer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteDealer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
