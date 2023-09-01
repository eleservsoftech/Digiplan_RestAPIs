package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_cases")
public class Cases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caseId;
    private String submittedOn = LocalDate.now().toString();
    private String treatmentLink;
    private String downloadLink;
    @JsonIgnore
    @JsonProperty(value = "formdata")
    private String formData;
    private String submittedBy;
    private String remarks;
    @Column(name = "plan_status")
    private String planStatus;
    @Column(name = "term_condition_status")
    private Integer termConditionStatus;
    @Column(name = "doctor_name")
    private String doctorName;
    private String groupId;

//    @Transient
//    private byte[] Image;

}
