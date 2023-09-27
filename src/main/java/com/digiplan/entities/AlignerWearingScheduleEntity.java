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

    @Column(name = "aligner_no")
    private String alignerNo;

    @Column(name = "planned_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedDate;

    @Column(name = "actual_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualDate;

    private String remarks;
    private String user;

}
