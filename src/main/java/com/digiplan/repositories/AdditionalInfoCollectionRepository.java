package com.digiplan.repositories;

import com.digiplan.entities.AdditionalInfoCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdditionalInfoCollectionRepository extends JpaRepository<AdditionalInfoCollection, Long> {
    List<AdditionalInfoCollection> findByFormId(Long formId);
}



