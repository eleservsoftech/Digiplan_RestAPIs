package com.digiplan.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "alignwise_query")
public class Query {

    @Id
    private String queryId = String.valueOf(new Date().getTime());
    private String customerName;
    private Long phoneNumber;
    private String email;
    private String queryText;

}
