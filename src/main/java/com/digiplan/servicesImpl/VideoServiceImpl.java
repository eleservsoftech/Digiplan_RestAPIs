package com.digiplan.servicesImpl;

import com.digiplan.entities.Video;
import com.digiplan.repositories.VideoRepository;
import com.digiplan.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Value("${video.upload.location}")
    private String uploadLocation;

    @Override
    public List<Video> getAllVideos() {
        List<Video> videos = new ArrayList<>();

        File folder = new File(uploadLocation);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    try {
                        byte[] fileData = Files.readAllBytes(file.toPath());
                        videos.add(new Video(file.getName(), fileData));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return videos;
    }

    @Override
    public boolean updateVideoById(Long id, MultipartFile file) {
        try {
            if (id <= 5) {
                String fileName = id + ".mp4";
                byte[] fileData = file.getBytes();
                Path filePath = Paths.get(uploadLocation + fileName);
                Files.write(filePath, fileData);

                return true;
            } else {
                System.out.println("Error: You can't upload or update for id greater than 5");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
