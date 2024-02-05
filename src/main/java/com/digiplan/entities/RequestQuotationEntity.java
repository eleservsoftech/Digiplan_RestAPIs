package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "request_quotation")

public class RequestQuotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private Long formId;

    @Column(name = "submittedOn")
    private LocalDate submittedOn = LocalDate.now();
    @Column(name = "orthodonstist_name", length = 75)
    private String orthodonstistName;

    @Column(name = "phone", length =20)
    private String phone;

    @Column(name = "City",  length = 75)
    private String City ;

    @Column(name = "submittedby",  length = 75)
    private String submittedby ;
    @Column(name = "remarks",  length = 250)
    private String remarks ;

    @Column(name = "patientname" , length = 75)
    private String patientname ;

    @Column(name = "gender", length = 10)
    private String gender;


    @Column(name = "dob")
    private LocalDate dob;


    @Column(name = "photo1", length = 100)
    private String photo1;

    @Column(name = "photo2", length = 100)
    private String photo2;

    @Column(name = "photo3", length = 100)
    private String photo3;

    @Column(name = "photo4", length = 100)
    private String photo4;

    @Column(name = "photo5", length = 100)
    private String photo5;

    @Column(name = "flag" , length = 75)
    private String flag ;
    @Column(name = "doctor_name" , length = 75)
    private String doctorName ;
    @Column(name = "treatment_cost" , length = 75)
    private String treatmentCost ;

    @Column(name = "duration" , length = 75)
    private String duration ;

    @Column(name = "crm_status" , length = 75)
    private String crmStatus ;

    @Column(name = "crm_decision" , length = 75)
    private String crmDecision ;

    @Column(name = "crm_by" , length = 75)
    private String crmBy ;

    @Column(name = "crm_decesion_at")
    private LocalDateTime crmDecesionAt;

    @Column(name = "folder_name", length = 250)
    private String folderName;

}
