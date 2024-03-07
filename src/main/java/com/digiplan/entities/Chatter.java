package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "chatter")

public class Chatter {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private  Long Id;
    @Column(name = "formid")
    private Long FormId;

    @Column(name = "comment", length = 1000)
    private String Comment;

    @Column(name = "comment_by", length = 75)
    private String CommentBy;

    @Column(name = "isdoctor")
    private boolean IsDoctor;
    @Column(name = "ispublic")
    private boolean IsPublic;
    @Column(name = "isdocument")
    private boolean IsDocument;
    @Column(name = "created_at")
    private Date CreatedAt;

    @Column(name = "modified_at")
    private Date ModifiedAt;



}
