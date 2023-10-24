package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "draft_id")
    private Integer draftId;
    @Column(name = "form_id")
    private Integer formId;
    @Column(name = "folder_name")
    private String folderName;
    private String stage;
    private String side;
    private String front;
    private String frontSmiling;
    private String rightBuccal;
    private String leftBuccal;
    private String upperOcclusial;
    private String lowerOcclusial;
    private String frontal;
    private String opg;
    private String lateralCeph;
    private String other;
    private String pdf1;
    private String pdf2;
    @Column(name = "case_id")
    private String caseId;

}