package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @Column(name = "case_id")
    private String caseId;

    @Column(name = "user_name")
    private String username;

    private String stage;
    private String comment;
    private String planNo;

    private LocalDateTime date = LocalDateTime.now();
    @Column(name = "support_action", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean supportAction;



    @Transient
    private String Doctor_Name;

    @Transient
    private String Patient_Name;
}
