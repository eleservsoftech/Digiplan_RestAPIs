package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Component
@Table(name = "aligner_wearing_schedule")
public class AlignerWearingScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 10,max = 10,message = "Please add 10 digit valid Case Id")
    @Column(name = "case_id")
    private String caseId;

    @Column(name = "dispatched_id")
    private String dispatchedId;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "aligner_no_u")
    private String alignerNoU;

    @Column(name = "aligner_no_l")
    private String alignerNoL;

    @Column(name = "planned_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "actual_date")
    private Date actualDate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "sign")
    private String sign;

    @Column(name = "total_aligner_u")
    private String totalAlignerU;

    @Column(name = "total_aligner_l")
    private String totalAlignerL;




}
