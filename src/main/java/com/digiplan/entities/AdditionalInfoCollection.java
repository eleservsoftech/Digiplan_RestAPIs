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
@Table(name = "additional_info_collection")

public class AdditionalInfoCollection {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private  Long Id;
    @Column(name = "form_id")
    private Long FormId;

    @Column(name = "folder", length = 250)
    private String folderName;

    @Column(name = "imagepath", length = 2500)
    private String ImagePath;

    @Column(name = "image", length = 2500)
    private String Image;

    @Column(name = "imagetype", length = 2500)
    private String ImageType;

    @Column(name = "additional_info_remarks", length = 2500)
    private String AdditionalInfoRemarks;

    @Column(name = "created_at")
    private Date CreatedAt;


}
