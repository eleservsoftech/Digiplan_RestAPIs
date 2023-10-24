package com.digiplan.entities;

import java.time.LocalDate;

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
@Table(name = "alignwise_treatmentmethods")
public class TreatmentMethod {

    @Column(name = "incomplete_form_id")
    private Integer incompleteFormId;
    @Column(name = "incompleteform_form_id_submitted_on")
    private LocalDate submittedOn = LocalDate.now();
    @Column(name = "incompleteform_form_id_submitted_by")
    private String submittedBy;
    @Column(name = "incompleteform_form_id_patient_name")
    private String patientName;
    @Id
    @Column(name = "wizard11_form_id")
    private String id;
    @Column(name = "wizard11_form_id_submitted_on")
    private LocalDate wizardFormIdSubmittedOn = LocalDate.now();
    @Column(name = "wizard11_form_id_submittedby")
    private String wizardFormIdSubmittedBy;
    @Column(name = "wizard11_form_id_patientname")
    private String wizardFormPatientName;
    private String cob_IPR;
    private String cob_IPRAnterior;
    private String cob_IPRPosterior;
    private String cob_extraction;
    private String cob_tooth_no_for_extraction1;
    private String cob_tooth_no_for_extraction2;
    private String cob_tooth_no_for_extraction3;
    private String cob_tooth_no_for_extraction4;
    private String cmrb_upper_molar_distalization;
    private String cmrb_Right_distalization;
    private String cmrb_Left_distalization;
    private String cmrb_posterior_IPR;
    private String cmrb_tooth_no_for_elastics;
    private String ccr_IPR;
    private String ccr_IPRAnterior;
    private String ccr_IPRPosterior;
    private String ccr_extraction;
    private String ccr_tooth_no_for_extraction1;
    private String ccr_tooth_no_for_extraction2;
    private String ccr_tooth_no_for_extraction3;
    private String ccr_tooth_no_for_extraction4;
    private String bo_Biteopening;
    private String ar_IPR;
    private String ar_IPRAnterior;
    private String ar_IPRPosterior;
    private String ar_extraction;
    private String ar_tooth_no_for_extraction1;
    private String ar_tooth_no_for_extraction2;
    private String ar_tooth_no_for_extraction3;
    private String ar_tooth_no_for_extraction4;
    private String ar_elastics;
    private String fnbs_IPR;
    private String fnbs_IPRAnterior;
    private String fnbs_IPRPosterior;
    private String fnbs_elastics;
    private String fnbs_elasticsAP;
    private String ac_IPR;
    private String ac_IPRAnterior;
    private String ac_IPRPosterior;
    private String ac_extraction;
    private String ac_tooth_no_for_extraction1;
    private String ac_tooth_no_for_extraction2;
    private String ac_tooth_no_for_extraction3;
    private String ac_tooth_no_for_extraction4;
    private String ac_elastics;
    private String pc_upper_expansion;
    private String pc_upto_mm_upper_expansion;
    private String pc_lower_contraction;
    private String pc_upto_mm_lower_contraction;
    private String pos_hybrid_treatment;
    private String hybrid_treatment_remarks;
    private String attachment_selection;
    private String attachment_selection_tooth;
    private String Attachments_Upper;
    private String Attachments_Upper_tooth_no1;
    private String Attachments_Upper_tooth_no2;
    private String Attachments_Upper_tooth_no3;
    private String Attachments_Upper_tooth_no4;
    private String Attachments_extraction;
    private String Attachments_Lower_tooth_no1;
    private String Attachments_Lower_tooth_no2;
    private String Attachments_Lower_tooth_no3;
    private String Attachments_Lower_tooth_no4;
    private String Retainer_Retention;
    private String Retainer_Case_category;

}
