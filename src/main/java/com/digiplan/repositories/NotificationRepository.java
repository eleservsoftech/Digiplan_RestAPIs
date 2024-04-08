package com.digiplan.repositories;

import com.digiplan.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "CALL notification_list(?1, ?2)", nativeQuery = true)
    List<Object[]> callNotificationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "CALL DoctorNotification(?1)", nativeQuery = true)
    List<Object[]> callDoctorNotification(@Param("user_id") String user_id);
    @Transactional
    @Modifying
    @Query(value = "CALL notification_list()", nativeQuery = true)
    void callNotification_list();

   // call  notificationupdateisread ('Bhavna')
   @Transactional
   @Modifying
    @Query(value = "CALL notificationupdateisread(?1)", nativeQuery = true)
    void callNotificationupdateisread(@Param("user_id") String user_id);


    @Query(value = "select Count(id) As total from notifications where user_id=:user_id and is_read=0", nativeQuery = true)
    int noOfNotificationPending(@Param("user_id") String user_id );

    @Transactional
    @Modifying
    @Query(value = "CALL support_notification_list()", nativeQuery = true)
    void callSupportNotificationList();

    @Query(value = "select * from support_notifications\n" +
            "where is_read=0", nativeQuery = true)
    List<Object[]> callNotificationListForSupport();

    @Transactional
    @Modifying
    @Query(value = "Update support_notifications\n" +
            " set is_read=1, read_by=?1, readtime=now()\n" +
            " where id= ?2", nativeQuery = true)
    void updateSupportNotifications(@Param("user_id") String user_id,@Param("id") String id);

}


