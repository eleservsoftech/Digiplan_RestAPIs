package com.digiplan.controllers;

import com.digiplan.entities.Video;
import com.digiplan.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllVideos() {
        try {
            List<Video> videos = videoService.getAllVideos();

            List<Map<String, Object>> videoList = videos.stream()
                    .map(video -> {
                        Map<String, Object> videoData = new HashMap<>();
                        videoData.put("name", video.getName());
                        videoData.put("data", Base64.getEncoder().encodeToString(video.getData()));
                        return videoData;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("data", videoList);
            response.put("message", "Videos retrieved successfully");
            response.put("status", HttpStatus.OK.value());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateVideoById(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            boolean success = videoService.updateVideoById(id, file);

            if (success) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Video updated successfully");
                response.put("status", HttpStatus.OK.value());

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Video with given ID not found");
                response.put("status", HttpStatus.NOT_FOUND.value());

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Internal Server Error");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private static class VideoResponse {
        private String name;
        private String data;

        public VideoResponse() {
        }

        public VideoResponse(String name, byte[] data) {
            this.name = name;
            this.data = Base64.getEncoder().encodeToString(data);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadVideos(@RequestParam("videos") List<MultipartFile> videos) {
//        videoService.uploadVideos(videos);
//        return new ResponseEntity<>("Videos uploaded successfully", HttpStatus.OK);
//    }

}
