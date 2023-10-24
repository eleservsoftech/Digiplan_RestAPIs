package com.digiplan.entities;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_drafts")
public class Draft {

    private LocalDate savedOn = LocalDate.now();
    private String formData;
    private String savedBy;
    private Integer isActive;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "image1", length = 100)
    private String image1;
    @Column(name = "image2", length = 100)
    private String image2;
    @Column(name = "image3", length = 100)
    private String image3;
    @Column(name = "image4", length = 100)
    private String image4;
    @Column(name = "image5", length = 100)
    private String image5;
    @Column(name = "image6", length = 100)
    private String image6;
    @Column(name = "image7", length = 100)
    private String image7;
    @Column(name = "image8", length = 100)
    private String image8;
    @Column(name = "pdf1", length = 100)
    private String pdf1;
    @Column(name = "file_path", length = 250)
    private String file_path;

}
