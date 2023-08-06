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
@Table(name = "alignwise_basicinfopatient")
public class BasicPatientInfo {

    @Id
    private String caseId;
    private String patientName;
    private String gender;
    @Column(name="DATEOFBIRTH")
    private String dob;
    private String age;
    private String patientCategory;
    private String chiefComplaint;

}
