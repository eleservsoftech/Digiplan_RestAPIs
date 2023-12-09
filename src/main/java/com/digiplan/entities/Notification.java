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
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "message_from")
    private String messageFrom;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "read_time")
    private LocalDateTime readTime;

    // getters and setters
}

