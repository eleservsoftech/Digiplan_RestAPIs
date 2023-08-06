package com.digiplan.entities;

import java.time.LocalDate;

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
@Table(name = "alignwise_drafts")
public class Draft {

    private LocalDate savedOn = LocalDate.now();
    private String formData;
    private String savedBy;
    private Integer isActive;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
