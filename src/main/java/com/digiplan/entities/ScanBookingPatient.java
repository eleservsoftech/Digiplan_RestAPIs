package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "scan_booking_patient")
public class ScanBookingPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private String scanId;
    @Column(name = "case_id")
    private String caseId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "case_type")
    private String caseType;

    @Column(name = "scan_schedule_for")
    private String scanScheduleFor;

    @Column(name = "New")
    private String New;

    @Column(name = "Mid")
    private String mid;

    @Column(name = "refinement")
    private String refinement;

    @Column(name = "retainer")
    private String retainer;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_scan")
    private Date dateOfScan;

    @Column(name = "time_of_scan")
    private String timeOfScan;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;
    @Column(name = "request_id")
    private String requestId;


    @Column(name = "decision")
    private String decision;

    @Column(name = "Accepted")
    private String accepted;

    @Column(name = "cancel")
    private String cancel;

    @Column(name = "reschedule")
    private String reschedule;

    @Column(name = "have_you_decide_with_doctor")
    private String haveYouDecideWithDoctor;

    @Column(name = "reschedule_date")
    private Date rescheduleDate;

    @Column(name = "reschedule_time")
    private String rescheduleTime;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "isdelete")
    private boolean isDelete;

    @Column(name = "watts_user")
    private String wattsUser;

    @Column(name = "wattsuser_remarks")
    private String wattsUserRemarks;

    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;
}
