package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_users")
public class User {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "city")
    private String city;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    @Column(name = "typeofUser")
    private String typeofUser;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "groupId")
    private String groupId;
    @Column(name = "clinicName")
    private String clinicName;
    @Column(name = "buildingNo")
    private String buildingNo;
    @Column(name = "street")
    private String street;
    @Column(name = "state")
    private String state;
    @Column(name = "pin")
    private String pin;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name = "ttWattsProvider")
    private String ttWattsProvider;
    @Column(name = "promoted")
    private String promoted;
    @Column(name = "address")
    private String address;
    @Column(name = "dic_no")
    private String dic_no;
//    @Column(name = "doctor_unique_no")
   @Column(name = "doctor_unique_no", unique = true)
    private String doctor_unique_no;
    @Column(name = "type_of_doctor")
    private String type_of_doctor;
    @Column(name = "isdelete")
    private String isdelete;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Transient
    private String newPassword;
    @Transient
    private String confirmPassword;

    private int isdisable;

}
