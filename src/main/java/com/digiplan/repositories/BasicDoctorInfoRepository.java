package com.digiplan.repositories;

import com.digiplan.entities.BasicDoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicDoctorInfoRepository extends JpaRepository<BasicDoctorInfo, String> {
}
