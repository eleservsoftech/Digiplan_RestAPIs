package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @NotBlank(message = "Case Id is mandatory")
    @Size(min = 10, max = 20)
    private String caseId;
    private String submittedOn = LocalDate.now().toString();
    private String treatmentLink;
    private String downloadLink;
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

    @Transient
    private   byte[] Image;

}
