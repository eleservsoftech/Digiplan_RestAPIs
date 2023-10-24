package com.digiplan.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_basicinfopatient")
public class Patient {

    @Id
    private String caseId;
    private String patientName;
    private String gender;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;
    private String age;
    private String patientCategory;
    private String chiefComplaint;

}
