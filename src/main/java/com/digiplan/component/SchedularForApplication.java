package com.digiplan.component;

import com.digiplan.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularForApplication {
    @Autowired
    private NotificationRepository notificationRepository;
    @Scheduled(fixedRate = 60000) // 60,000 milliseconds = 1 minute
    public void myScheduledTask() {
        // Your scheduled task logic goes here
        System.out.println("Executing scheduled task at every minute.");
        notificationRepository.callNotification_list();
        notificationRepository.callSupportNotificationList();
    }
}
