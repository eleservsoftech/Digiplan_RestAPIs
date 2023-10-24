package com.digiplan.services;

import com.digiplan.entities.Draft;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DraftService {

    Draft getDraft(Integer id);

    List<Draft> getAllDrafts();

    Draft addDraft(Draft draftData);

    //testing aman

//    Draft addDraft(Draft draftData, MultipartFile image1, MultipartFile image2, MultipartFile image3,
//                   MultipartFile image4, MultipartFile image5, MultipartFile image6,  MultipartFile image7,  MultipartFile image8, MultipartFile pdf1, String file_path);

    Draft updateDraft(Integer id, Draft draftData);


   // String deleteDraft(Integer id);

    ResponseEntity<Map> viewDrafts(Draft draftData);

    ResponseEntity<Map> deleteDraft(String draftId);
}
