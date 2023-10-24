package com.digiplan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
   Optional<Image> findById(Integer id);

   Image findByDraftId(Integer draftId);
   Image findByFormId(Integer formId);
   Image findByCaseIdAndId(String caseId ,Integer id);




}
