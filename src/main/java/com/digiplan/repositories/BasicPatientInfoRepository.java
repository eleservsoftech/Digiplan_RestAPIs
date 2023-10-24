package com.digiplan.repositories;

import com.digiplan.entities.BasicPatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicPatientInfoRepository  extends JpaRepository<BasicPatientInfo, String> {
}
