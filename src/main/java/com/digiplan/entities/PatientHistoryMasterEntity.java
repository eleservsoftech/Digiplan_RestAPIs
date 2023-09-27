package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Component
@Table(name = "patient_history_master")
public class PatientHistoryMasterEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer action_id;
   private String action_name;
   private String sub_action;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="created_time", columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP")
   private Date created_time;
}
