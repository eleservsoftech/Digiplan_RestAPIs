package com.digiplan.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "incompleteform")
public class IncompleteForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private Integer id;
    private LocalDate submittedOn = LocalDate.now();
    private String formData;
    private String submittedBy;
    private String patientName;
    private String remarks;
    private String flag;
    @Column(name = "doctor_name")
    private String doctorName;
    private String groupId;
    @Column(name = "treatment_cost")
    private BigDecimal treatmentCost;
    private String duration;
    @Column(name = "crm_status")
    private String crmStatus;
    @Column(name = "crm_decision")
    private String crmDecision;
    @Column(name = "crm_by")
    private String crmBy;
    @Column(name = "crm_decesion_at")
    private LocalDateTime crmDecesionAt;


}
