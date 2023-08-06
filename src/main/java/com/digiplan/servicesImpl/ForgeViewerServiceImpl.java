package com.digiplan.servicesImpl;

import com.digiplan.entities.ForgeViewer;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.ForgeViewerRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.ForgeViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ForgeViewerServiceImpl implements ForgeViewerService {

    @Autowired
    private ForgeViewerRepository forgeViewerRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public ForgeViewer getForgeViewer(Integer id) {
        ForgeViewer forgeViewer = null;
        try {
            Optional<ForgeViewer> check = forgeViewerRepository.findById(id);
            if (check.isPresent())
                forgeViewer = forgeViewerRepository.getById(id);
        } catch (Exception exception) {
            System.out.println("@getForgeViewer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getForgeViewer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return forgeViewer;
    }

    @Override
    public List<ForgeViewer> getAllForgeViewers() {
        List<ForgeViewer> forgeViewersList = null;
        try {
            forgeViewersList = forgeViewerRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllForgeViewers Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllForgeViewers", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return forgeViewersList;
    }

    @Override
    public ForgeViewer addForgeViewer(ForgeViewer forgeViewerData) {
        ForgeViewer forgeViewer = null;
        try {
            forgeViewerData.setCreateDate(LocalDateTime.now());
            forgeViewer = forgeViewerRepository.saveAndFlush(forgeViewerData);
        } catch (Exception exception) {
            System.out.println("@addForgeViewer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addForgeViewer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return forgeViewer;
    }

    @Override
    public ForgeViewer updateForgeViewer(Integer id, ForgeViewer forgeViewerData) {
        ForgeViewer forgeViewer = null;
        try {
            forgeViewerData.setUpdatedDate(LocalDateTime.now());
            Optional<ForgeViewer> check = forgeViewerRepository.findById(id);
            if (check.isPresent())
                forgeViewer = forgeViewerRepository.saveAndFlush(forgeViewerData);
        } catch (Exception exception) {
            System.out.println("@updateForgeViewer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateForgeViewer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return forgeViewer;
    }

    @Override
    public String deleteForgeViewer(Integer id) {
        String status = "";
        try {
            forgeViewerRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteForgeViewer Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteForgeViewer", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

}
