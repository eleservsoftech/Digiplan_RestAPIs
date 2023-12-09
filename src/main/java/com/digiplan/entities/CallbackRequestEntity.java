package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "callback_requests")
public class CallbackRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId")
    private Long requestId;

//    @NotNull
//    @NotBlank
    @Size(min = 10,max = 10,message = "Please add 10 digit valid Case Id")
    private String caseId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ptName", length = 100)
    private String ptName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "drName", length = 100)
    private String drName;

    @Pattern(regexp="(^$|[0-9]{10})")
    @Column(name = "drPhoneNumber", length = 15)
    private String drPhoneNumber;

    @Email
    @Column(name = "DrEmail", length = 100)
    @Size(max = 100,message = "Email cannot be more than 100 digit")
    private String DrEmail;

    @NotBlank
    @Size(max = 100)
    @Column(name = "TypeOfQuery", length = 100)
    private String typeOfQuery;

    @Size(max = 250)
    @Column(name = "remarks", length = 250)
    private String remarks;

    @NotNull
    @Column(name = "RequestDate")
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @Column(name = "isdelete", columnDefinition = "tinyint default 0")
    private boolean isDelete;
}