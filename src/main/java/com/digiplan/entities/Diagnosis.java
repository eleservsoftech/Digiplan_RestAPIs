package com.digiplan.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "alignwise_diagnosis")
public class Diagnosis {

    @Column(name = "incomplete_form_id")
    private Integer incompleteFormId;
    @Id
    @Column(name = "wizard6_form_id")
    private String id;
    @Column(name = "if_submitted_on")
    private LocalDateTime submittedOn = LocalDateTime.now();
    @Column(name = "if_submittedby")
    private String submittedBy;
    @Column(name = "if_patientname")
    private String patientName;
    @Column(name = "tongue_normal")
    private Integer tongueNormal;
    @Column(name = "tongue_abnormal")
    private Integer tongueAbnormal;
    @Column(name = "habbits_none")
    private Integer habbitsNone;
    @Column(name = "habbits_tongue_thrusting")
    private Integer habbitsTongueThrusting;
    @Column(name = "habbits_mouth_breathing")
    private Integer habbitsMouthBreathing;
    @Column(name = "habbits_thump_sucking")
    private Integer habbitsThumbSucking;
    @Column(name = "habbits_gingval_stripping")
    private Integer habbitsGingvalStripping;
    @Column(name = "molar_relations")
    private String molarRelations;
    @Column(name = "molar_relations_others")
    private String molarRelationsOthers;
    @Column(name = "canine_relations")
    private String canineRelations;
    @Column(name = "canine_relations_others")
    private String canineRelationsOthers;
    private Double overjet;
    private Double overbit;
    @Column(name = "ciop_upper")
    private Integer ciopUpper;
    @Column(name = "ciop_lower")
    private Integer ciopLower;
    @Column(name = "crossbit_ar")
    private Integer crossbitAR;
    @Column(name = "crossbit_al")
    private Integer crossbitAL;
    @Column(name = "crossbite_pr")
    private Integer crossbitePR;
    @Column(name = "crossbite_pl")
    private Integer crossbitePL;
    @Column(name = "midline_ul")
    private Double midlineUL;
    @Column(name = "midline_ur")
    private Double midlineUR;
    @Column(name = "midline_ll")
    private Double midlineLL;
    @Column(name = "midline_lr")
    private Double midlineLR;
    private String lips;
    private String profile;
    @Column(name = "soft_tissue_facial_asymmetry")
    private String softTissueFacialAsymmetry;
    @Column(name = "skeletal_base_relation")
    private String skeletalBaseRelation;
    @Column(name = "skeletal_base_sub_relation")
    private String skeletalBaseSubRelation;
    @Column(name = "seletal_base_lateral_ceph")
    private String seletalBaseLateralCeph;
    @Column(name = "hard_tissue_facial_asymmetry")
    private String hardTissueFacialAsymmetry;
    @Column(name = "tmj_findings")
    private String tmjFindings;
    @Column(name = "type_of_malocclusion")
    private String typeOfMalocclusion;
    @Column(name = "malocclusion_div")
    private String malocclusionDiv;
    @Column(name = "type_of_malocclusion_other")
    private String typeOfMalocclusionOther;
    @Column(name = "comments_wizard_6")
    private String commentsWizard;

}
