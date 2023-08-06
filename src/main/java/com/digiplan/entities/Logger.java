package com.digiplan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@NoArgsConstructor
@Table(name = "logger")
public class Logger {

    @Id
    @Column(name = "correlation_id")
    private String correlationId;
    private String api;
    @Column(name = "exception_message")
    private String exceptionMessage;
    @Column(name = "exception_description")
    private String exceptionDescription;
    @Column(name = "logged_date")
    private LocalDateTime loggedDate;

}
