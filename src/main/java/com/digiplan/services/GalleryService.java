package com.digiplan.services;

import com.digiplan.entities.Gallery;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GalleryService {
    Gallery getGalleryData(String caseId);

    List<Gallery> getAllGalleryData();

    Gallery addGalleryData(Gallery galleryData);

    Gallery updateGalleryData(String caseId, Gallery galleryData);

    String deleteGalleryData(String caseId);

    ResponseEntity<Map> getSamples();

    ResponseEntity<Map> getSampleFilterData(String caseType, String caseCategory, String gender);
}
