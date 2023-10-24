package com.digiplan.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "alignwise_case_upload")
@Component
public class CaseUploadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "case_id",length = 12)
    private long case_Id;

    @Column(name = "plan_no",length = 2)
    private String plan_No;

    @Column(name = "case_type",length = 60)
    private  String case_Type;

    @Column(name = "case_category",length = 60)
    private  String case_Category;

    @Column(name = "main_img",length = 100)
    private  String main_Img;

    @Column(name = "patient_properties",length = 50)
    private  String patient_properties;

    @Column(name = "report_pdf",length = 50)
    private  String report_pdf;

    @Column(name = "upper_ipr",length = 50)
    private  String upper_ipr;

    @Column(name = "lower_ipr",length = 50)
    private  String lower_ipr;

    @Column(name = "front_video",length = 50)
    private  String front_video;

    @Column(name = "upper_video",length = 50)
    private  String upper_video;

    @Column(name = "lower_video",length = 50)
    private  String lower_video;

    @Column(name = "left_video",length = 50)
    private  String left_video;

    @Column(name = "right_video",length = 50)
    private  String right_video;

    @Column(name = "is_deleted",length = 2)
    //@Column(name="is_deleted", columnDefinition=" varchar(2) default 'N'")
    private  String is_deleted="N";

    @Column(name = "status",length = 2)
    //@Column(name="status", columnDefinition=" varchar(2) default 'Y'")
    //@Column(columnDefinition = "status varchar(2) default 'Y'")
    private  String status="Y";

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "modified_at", updatable = true)
    private LocalDateTime modified_at;
}
