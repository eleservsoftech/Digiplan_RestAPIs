package com.digiplan.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "forge_viewer_data")
public class ForgeViewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;
    private String caseId;
    private String type1;
    private String type2;
    @Column(name = "created_date")
    private LocalDateTime createDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "forge_viewer_link")
    private String forgeViewerLink;

}
