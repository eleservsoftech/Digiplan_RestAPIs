package com.digiplan.services;

import com.digiplan.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {

//    Image getImage(Integer id);
    ResponseEntity<Map<String, Object>> getImage(Integer id);
    ResponseEntity<Map<String, Object>> getImagebyDraftId(Integer draftId);
    ResponseEntity<Map<String, Object>> getImagebyFormId(Integer formId);
    ResponseEntity<Map<String, Object>> getImagebyCaseId(String caseId , Integer id);




    List<Image> getAllImages();

    ResponseEntity<Map> addImage(Image imageData);

    Image updateImage(Integer id, Image imageData);

    String deleteImage(Integer id);

    ResponseEntity<Map> uploadPPFFiles(Integer draftId, Integer formId, MultipartFile side, MultipartFile front, MultipartFile frontSmiling,
                                       MultipartFile rightBuccal, MultipartFile leftBuccal, MultipartFile upperOcclusial, MultipartFile lowerOcclusial,
                                       MultipartFile frontal, MultipartFile opg, MultipartFile lateralCeph, MultipartFile other, MultipartFile pdf1,
                                       MultipartFile pdf2, String caseId, String patientName);

}
