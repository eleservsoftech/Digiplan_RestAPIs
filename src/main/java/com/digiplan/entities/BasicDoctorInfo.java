package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_basicinfo")
public class BasicDoctorInfo {

    @Id
    private String caseId;
    private String doctorName;
    private String phoneNumber;
    private String treatingDoctor;
    @Column(name="TREATINGDOCTORPHONE")
    private String treatingDoctorPhoneNumber;
    private String clinicAddress;
    private String city;
    private String clinicEmail;

}
