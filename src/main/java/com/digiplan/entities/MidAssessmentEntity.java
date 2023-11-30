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

<<<<<<< HEAD
        @Column(name = "32watts_user_remarks", length = 200)
        private String watts32UserRemarks;

        @Column(name = "32watts_user", length = 80)
        private String watts32User;

        @Column(name = "file_path", length = 250)
        private String folderName;
=======
        @Column(name = "wattsuser_remarks", length = 200)
        private String wattsuser_remarks;

        @Column(name = "watts_user", length = 80)
        private String watts_user;

        @Column(name = "file_path", length = 250)
        private String folderName;
        @Column(name = "created_date", length = 250)
        private String created_date;
        @Column(name = "request_status", length = 250)
        private String request_status;
>>>>>>> dc1c60c32ce1e289ce60f7020d684461ad5179db
 }
