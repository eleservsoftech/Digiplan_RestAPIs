package com.digiplan.entities;

public interface NotificationCall {
    String  getid();
    String  getuser_id();
    String  getmessage();
    String  getmessage_from();
    String  getcreated_at();
    String  getis_read();
    String  getread_time();
}
