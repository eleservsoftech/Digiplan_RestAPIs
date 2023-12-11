package com.digiplan.services;

import com.digiplan.entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    List<Video> getAllVideos();
    boolean updateVideoById(Long id, MultipartFile file);
//    void uploadVideos(List<MultipartFile> videos);
}
