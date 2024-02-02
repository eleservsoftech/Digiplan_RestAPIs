package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Component
@Table(name = "mid_assessment")
public class MidAssessmentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "request_id")
        private Long requestId;

        @Column(name = "Caseid")
        private String caseId;

        @Column(name = "patient_name", length = 75)
        private String patientName;

        @Column(name = "doctor_name", length = 75)
        private String doctorName;

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

        @Column(name = "photo6", length = 100)
        private String photo6;

        @Column(name = "photo7", length = 100)
        private String photo7;


        @Column(name = "photo8", length = 100)
        private String photo8;

        @Column(name = "photo9", length = 100)
        private String photo9;
        @Column(name = "photo10", length = 100)
        private String photo10;

        @Column(name = "aligner_no_u")
        private String  alignerNoU;

        @Column(name = "aligner_no_l")
        private String alignerNoL;

        @Column(name = "fitting_of_aligner", length = 50)
        private String fittingOfAligner;

        @Column(name = "remarks", length = 200)
        private String remarks;

        @Column(name = "user", length = 80)
        private String user;

        @Column(name = "wattsuser_remarks", length = 200)
        private String watts32UserRemarks;

        @Column(name = "watts_user", length = 80)
        private String watts32User;

        @Column(name = "file_path", length = 250)
        private String folderName;
 }
