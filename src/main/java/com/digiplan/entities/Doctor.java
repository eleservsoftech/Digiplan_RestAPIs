package com.digiplan.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_basicinfo")
public class Doctor {

    @Id
    private String caseId;
    private String doctorName;
    private String phoneNumber;
    private String treatingDoctor;
    private String treatingDoctorPhone;
    private String clinicAddress;
    private String city;
    private String clinicEmail;

}
