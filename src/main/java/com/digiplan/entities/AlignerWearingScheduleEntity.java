package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    @Column(name = "case_id")
    private long caseId;

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
