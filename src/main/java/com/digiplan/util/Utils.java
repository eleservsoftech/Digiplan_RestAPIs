
package com.digiplan.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class Utils {
    /*===========================================================================*/
    /*    Code to get the request status from the Request Log table              */
    /*===========================================================================*/
    @Autowired
    private Environment env;
    //@Value( "${file.upload.location}" )
    //static String location;
    public  void Directires(String case_id, MultipartFile main_Img, MultipartFile patient_properties, MultipartFile report_pdf,
                            MultipartFile upper_ipr,MultipartFile lower_ipr,MultipartFile front_video,
                            MultipartFile upper_video,MultipartFile lower_video,MultipartFile left_video,
                            MultipartFile right_video) throws IOException {

        String path = env.getProperty("file.upload.location");

        System.out.println("Directires env:  "+path);

        try {
            File file = new File(env.getProperty("file.upload.location") + String.valueOf(case_id));

            System.out.println("file: "+file);

            File file1 = new File(file.getPath() + File.separator + "IPR");
            System.out.println("file1: "+file1);

            File file2 = new File(file.getPath() + File.separator + "Videos");
            System.out.println("file2: "+file2);

            if (!file.exists())
                file.mkdir();
            if (!file1.exists())
                file1.mkdir();
            if (!file2.exists())
                file2.mkdir();

            List<MultipartFile> list1 = Arrays.asList(main_Img, patient_properties, report_pdf);
            List<MultipartFile> list2 = Arrays.asList(upper_ipr, lower_ipr);
            List<MultipartFile> list3 = Arrays.asList(front_video, upper_video, lower_video, left_video, right_video);

            if (!list1.isEmpty() || !list2.isEmpty() || !list3.isEmpty()) {
                for (MultipartFile uploadedFile : list1) {
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(File.separator + file + File.separator + uploadedFile.getOriginalFilename());
                        System.out.println("main:: " + mainUpload.getPath());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
                for (MultipartFile uploadedIpr : list2) {
                    if (!uploadedIpr.isEmpty()) {
                        File iprUpload = new File(File.separator + file1 + File.separator + uploadedIpr.getOriginalFilename());
                        System.out.println("ipr:: " + iprUpload.getPath());
                        uploadedIpr.transferTo(iprUpload);
                    }
                }
                for (MultipartFile uploadedVideo : list3) {
                    if (!uploadedVideo.isEmpty()) {
                        File mainUpload = new File(File.separator + file2 + File.separator + uploadedVideo.getOriginalFilename());
                        System.out.println("video:: " + mainUpload.getPath());
                        uploadedVideo.transferTo(mainUpload);
                    }
                }
            }
        } catch (
                EOFException eof) {
            eof.printStackTrace();
        }
    }


    public  void Directires1(String case_id, MultipartFile main_Img, MultipartFile patient_properties, MultipartFile report_pdf,MultipartFile upper_ipr,MultipartFile lower_ipr,MultipartFile front_video,MultipartFile upper_video,MultipartFile lower_video,MultipartFile left_video,MultipartFile right_video) throws IOException {

        try {

            File file = new File(env.getProperty("file.upload.location") + String.valueOf(case_id));
            System.out.println("file: "+file);

            File file1 = new File(file.getPath() + File.separator + "IPR");
            System.out.println("file1: "+file1);

            File file2 = new File(file.getPath() + File.separator + "Videos");
            System.out.println("file2: "+file2);

            if (!file.exists())
                file.mkdir();
            if (!file1.exists())
                file1.mkdir();
            if (!file2.exists())
                file2.mkdir();

            List<MultipartFile> list1 = Arrays.asList(main_Img, patient_properties, report_pdf);
            List<MultipartFile> list2 = Arrays.asList(upper_ipr, lower_ipr);
            List<MultipartFile> list3 = Arrays.asList(front_video, upper_video, lower_video, left_video, right_video);

            if (!list1.isEmpty() || !list2.isEmpty() || !list3.isEmpty()) {
                for (MultipartFile uploadedFile : list1) {
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(File.separator + file + File.separator + uploadedFile.getOriginalFilename());
                        System.out.println("main:: " + mainUpload.getPath());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
                for (MultipartFile uploadedIpr : list2) {
                    if (!uploadedIpr.isEmpty()) {
                        File iprUpload = new File(File.separator + file1 + File.separator + uploadedIpr.getOriginalFilename());
                        System.out.println("ipr:: " + iprUpload.getPath());
                        uploadedIpr.transferTo(iprUpload);
                    }
                }
                for (MultipartFile uploadedVideo : list3) {
                    if (!uploadedVideo.isEmpty()) {
                        File mainUpload = new File(File.separator + file2 + File.separator + uploadedVideo.getOriginalFilename());
                        System.out.println("video:: " + mainUpload.getPath());
                        uploadedVideo.transferTo(mainUpload);
                    }
                }
            }
        } catch (
                EOFException eof) {
            eof.printStackTrace();
        }
    }

    //    public static void iprDir(String case_id, MultipartFile upper_ipr, MultipartFile lower_ipr) throws IOException {
//        System.out.println("iprDir:====> " + upper_ipr);
//
//        try {
//            if (!upper_ipr.isEmpty() || !lower_ipr.isEmpty()) {
//
//                File file = new File("D://casesData/" + String.valueOf(case_id));
//                //lebel-2.1
//                File file1 = new File(file.getPath() + File.separator + "IPR");
//                //environment.getProperty("IPR"));
//                List<MultipartFile> iprList = Arrays.asList(upper_ipr, lower_ipr);//,MultipartFile upper_ipr,MultipartFile lower_ipr,MultipartFile front_video,MultipartFile upper_video,MultipartFile lower_video,MultipartFile left_video,MultipartFile right_video););
//
//                if (!file1.exists()) {
//                    file1.mkdir();
//                    for (MultipartFile uploadedIpr : iprList) {
//                        File iprUpload = new File(File.separator + file1 + File.separator + uploadedIpr.getOriginalFilename());
//                        uploadedIpr.transferTo(iprUpload);
//                    }
//                } else {
//                    for (MultipartFile uploadedIpr : iprList) {
//                        File iprUpload = new File(File.separator + file1 + File.separator + uploadedIpr.getOriginalFilename());
//                        uploadedIpr.transferTo(iprUpload);
//                    }
//                }
//            }
//        } catch (EOFException eof) {
//            eof.printStackTrace();
//        }
//    }
//    public static void videoDir(String case_id, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video) throws IOException {
//        try {
//
//
//            //lebel-2.1
//            if (!front_video.isEmpty() || !upper_video.isEmpty() || !lower_video.isEmpty() || !left_video.isEmpty() || !right_video.isEmpty()) {
//                File file = new File("D://casesData/" + String.valueOf(case_id));
//                File file2 = new File(file.getPath() + File.separator + "Videos");
//                List<MultipartFile> videosList = Arrays.asList(front_video, upper_video, lower_video, left_video, right_video);
//
//                if (!file2.exists()) {
//                    file2.mkdir();
//                    for (MultipartFile uploadedVideos : videosList) {
//                        File videoUpload = new File(File.separator + file2 + File.separator + uploadedVideos.getOriginalFilename());
//                        uploadedVideos.transferTo(videoUpload);
//                    }
//                } else {
//                    for (MultipartFile uploadedVideos : videosList) {
//                        File videoUpload = new File(File.separator + file2 + File.separator + uploadedVideos.getOriginalFilename());
//                        uploadedVideos.transferTo(videoUpload);
//                    }
//                }
//            }
//        } catch (EOFException eof) {
//            eof.printStackTrace();
//        }
//    }
//
//
//    public static void updateDirectoriesFiles(String case_id, MultipartFile main_Img, MultipartFile patient_properties, MultipartFile report_pdf, MultipartFile upper_ipr, MultipartFile lower_ipr, MultipartFile front_video, MultipartFile upper_video, MultipartFile lower_video, MultipartFile left_video, MultipartFile right_video) throws IOException {
//
//        try {
//            File file = new File("D://casesData/" + String.valueOf(case_id));
//
//            if (file.exists()) {
//                List<MultipartFile> mainList = Arrays.asList(main_Img, patient_properties, report_pdf);
//                if (mainList.size() != 0) {
//                    for (MultipartFile uploadedFile : mainList) {
//                        File mainUpload = new File(File.separator + file + File.separator + uploadedFile.getOriginalFilename());
//                        uploadedFile.transferTo(mainUpload);
//                    }
//                }
//            } else {
//                log.info("no directory found!");
//            }
//        } catch (EOFException eof) {
//            eof.printStackTrace();
//        }
//        // other dir
//    }

// Mid scan add and update images
/*    public void uploadMidScanPhotos(String folderName,MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4)
    {
        File fileupload = null;

        System.out.println("utils uploadMidScanPhotos photo1: "+photo1);
        try {
            File file = new File(env.getProperty("file.midscan.location") +folderName);//add folder
            if (!file.exists()) {
                file.mkdir();
                List<MultipartFile> list = Arrays.asList(photo1,photo2,photo3,photo4);
                for (MultipartFile uploadedFile : list) {
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(File.separator + file + File.separator + uploadedFile.getOriginalFilename());
                        System.out.println("main-1:: " + mainUpload.getPath());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }
            else if (file.exists()) {
                List<MultipartFile> list = Arrays.asList(photo1,photo2,photo3,photo4);
                for (MultipartFile uploadedFile : list) {
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(File.separator + file + File.separator + uploadedFile.getOriginalFilename());
                        System.out.println("main-2:: " + mainUpload.getPath());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }
            else {
                log.info("No Directory exist with!");
            }
        }catch (Exception e)
        {
            log.info("message: "+e.getMessage());
        }
    }*/

    public void uploadMidScanPhotos(String folderName, MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                                    MultipartFile photo5,MultipartFile photo6, MultipartFile photo7, MultipartFile photo8, MultipartFile photo9,MultipartFile photo10)
    {
        File fileupload = null;
        try {
            File file = new File(env.getProperty("file.midscan.location") + folderName);

            if (!file.exists()) {
                file.mkdir();
                List<MultipartFile> photos = Arrays.asList(photo1, photo2, photo3, photo4,photo5, photo6, photo7, photo8,photo9,photo10);
                System.out.println(photos.size());
                for (MultipartFile uploadedFile : photos) {
                    System.out.println(uploadedFile.isEmpty());
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(file.getAbsolutePath() + File.separator + uploadedFile.getOriginalFilename());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }else{
                List<MultipartFile> photos = Arrays.asList(photo1, photo2, photo3, photo4,
                        photo5, photo6, photo7, photo8,photo9,photo10);
                for (MultipartFile uploadedFile : photos) {
                    if (!uploadedFile.isEmpty()) {
                        System.out.println("name1="+uploadedFile.getOriginalFilename());
                        File mainUpload = new File(file.getAbsolutePath() + File.separator + uploadedFile.getOriginalFilename());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }
        } catch (Exception e) {
            log.info("message: uploadMidScanPhotos{0} " + e.getMessage());
        }
    }


    public void uploadRequestQuotationPhotos(String folderName, MultipartFile photo1, MultipartFile photo2, MultipartFile photo3, MultipartFile photo4,
                                    MultipartFile photo5)
    {
        File fileupload = null;
        try {
            File file = new File(env.getProperty("file.requestQuotation.location") + folderName);
            System.out.println("file="+file.getPath());
            if (!file.exists()) {
                file.mkdir();
                List<MultipartFile> photos = Arrays.asList(photo1, photo2, photo3, photo4,photo5);
                System.out.println(photos.size());
                for (MultipartFile uploadedFile : photos) {
                    System.out.println(uploadedFile.isEmpty());
                    if (!uploadedFile.isEmpty()) {
                        File mainUpload = new File(file.getAbsolutePath() + File.separator + uploadedFile.getOriginalFilename());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }else{
                List<MultipartFile> photos = Arrays.asList(photo1, photo2, photo3, photo4,
                        photo5);
                for (MultipartFile uploadedFile : photos) {
                    if (!uploadedFile.isEmpty()) {
                        System.out.println("name1="+uploadedFile.getOriginalFilename());
                        File mainUpload = new File(file.getAbsolutePath() + File.separator + uploadedFile.getOriginalFilename());
                        uploadedFile.transferTo(mainUpload);
                    }
                }
            }
        } catch (Exception e) {
            log.info("message: uploadMidScanPhotos{0} " + e.getMessage());
        }
    }




}
