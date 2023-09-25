package com.digiplan.repositories;

import com.digiplan.entities.AlignerWearingScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlignerWearingScheduleRepository extends JpaRepository<AlignerWearingScheduleEntity,Integer> {
}
