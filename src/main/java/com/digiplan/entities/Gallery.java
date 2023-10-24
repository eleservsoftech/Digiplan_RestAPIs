package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "gallery")
public class Gallery {

    @Id
    private String caseId;
    private String submittedOn = LocalDate.now().toString();
    private String treatmentLink;
    private String downloadLink;
    private String formData;
    private String submittedBy;
    private String remarks;
    @Column(name = "case_type")
    private String caseType;
    @Column(name = "case_category")
    private String caseCategory;
    private String gender;

}
