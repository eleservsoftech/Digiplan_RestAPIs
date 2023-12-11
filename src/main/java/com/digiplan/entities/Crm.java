package com.digiplan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Component
@Table(name = "alignwise_crm")
public class Crm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crm_id")
    private Long crmId;
    @Column(name = "crm_name", length = 70)
    private String crmName;
}
