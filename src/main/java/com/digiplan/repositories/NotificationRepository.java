package com.digiplan.repositories;

import com.digiplan.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query(value = "CALL notification_list(?1, ?2)", nativeQuery = true)
    List<Object[]> callNotificationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}


