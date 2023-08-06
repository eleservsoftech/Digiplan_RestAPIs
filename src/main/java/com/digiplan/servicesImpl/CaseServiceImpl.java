package com.digiplan.servicesImpl;

import com.digiplan.entities.*;
import com.digiplan.repositories.*;
import com.digiplan.services.CaseService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private BasicDoctorInfoRepository basicDoctorInfoRepository;
    @Autowired
    private BasicPatientInfoRepository basicPatientInfoRepository;
    @Autowired
    private IncompleteFormRepository incompleteFormRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public List<Cases> getAllCases() {
        List<Cases> casesList = null;
        try {
            casesList = caseRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllCases Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllCases", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return casesList;
    }

    @Override
    public Cases addCase(Cases casesData) {
        Cases cases = null;
        try {
            cases = caseRepository.saveAndFlush(casesData);
        } catch (Exception exception) {
            System.out.println("@addCase Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addCase", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return cases;
    }

    @Override
    public ResponseEntity<Map> myCases(String username) {
        Map map = new HashMap();
        HttpStatus status = null;
        List<String> userList_username = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        Boolean doctorAdminCheck = false;
        try {
            List<User> userList = userRepository.findAllUsersList(username);
            List<Cases> casesList = caseRepository.findAll();
            if (!userList.isEmpty() || userList != null) {
                doctorAdminCheck = true;
            }
            for (User user : userList) {
                userList_username.add(user.getUsername());
            }
            for (Cases cases : casesList) {
                if (cases.getFormData() != null && cases.getFormData() != "") {
                    JSONObject jsonObject = new JSONObject();
                    JSONObject extractedData = (JSONObject) jsonParser.parse(cases.getFormData());
                    String casesList_username = extractedData.get("user").toString();
                    if (casesList_username != null && (casesList_username.equals(username) || (doctorAdminCheck && userList_username.contains(casesList_username)))) {
                        jsonObject.put("patientName", extractedData.get("PatientName"));
                        jsonObject.put("serialNumber", extractedData.get("serialnumber"));
                        jsonObject.put("dob", extractedData.get("DOB"));
                        jsonObject.put("date", extractedData.get("date"));
                        jsonObject.put("data", extractedData);
                        jsonArray.add(jsonObject);
                    }
                }
            }
            if (jsonArray.isEmpty()) {
                map.put("status", 404);
                map.put("message", "No data found");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", jsonArray);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("@myCases Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "myCases", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

//    @Override
//    public ResponseEntity<Map> myCases(String username) {
//        List<Cases> caseData = new ArrayList<>();
//        Map map = new HashMap();
//        HttpStatus status = null;
//        try {
//            List<Cases> casesList = caseRepository.findAll();
//            for (Cases item : casesList) {
//                if (item.getFormData() != null && item.getFormData() != "") {
//                    if (item.getFormData().contains("\"user\":\"" + username + "\"")) {
//                        caseData.add(item);
//                    }
//                }
//            }
//            if (caseData.isEmpty()) {
//                map.put("status", 404);
//                map.put("message", "No data found");
//                status = HttpStatus.NOT_FOUND;
//            } else {
//                map.put("status", 200);
//                map.put("message", "OK");
//                map.put("data", caseData);
//                status = HttpStatus.OK;
//            }
//        } catch (Exception exception) {
//            System.out.println("@myCases Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "myCases", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//            map.put("status", 500);
//            map.put("message", "Internal Server Error");
//            map.put("error", exception.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(map, status);
//    }

    @Override
    public ResponseEntity<Object> downloadReport(String caseId) {
        ResponseEntity<Object> responseEntity = null;
        try {
            String reportPath = environment.getProperty("report.download.path") + caseId + "/Report.pdf";
            File file = new File(reportPath);
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName() + "")
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(inputStreamResource);
        } catch (Exception exception) {
            System.out.println("@downloadReport Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "downloadReport", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map> getCaseDetails(String caseId) {
        HttpStatus status = null;
        Map<String, Object> map = new HashMap();
        Map<String, Object> data = new HashMap();
        try {
            String filePath = environment.getProperty("case.info.path") + caseId + "/patientprorperties.properties";
            File file = new File(filePath);
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(new File(filePath));
                Properties properties = new Properties();
                properties.load(inputStream);
                Iterator iterator = properties.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    data.put(key, properties.getProperty(key));
                }
                map.put("status", 200);
                map.put("message", "Record Found");
                map.put("data", data);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Record Not Found");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getCaseDetails Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getCaseDetails", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    public ResponseEntity<Map> uploadFiles(List<MultipartFile> file) {
        HttpStatus status = null;
        Map<String, Object> map = new HashMap();
        List urls = new ArrayList();
        try {
            List<File> files = new ArrayList<>();
            String path = environment.getProperty("upload.file.path");
            File fileData = null;
            for (MultipartFile uploadedFile : file) {
                if (uploadedFile.isEmpty() == false && uploadedFile.getSize() != 0) {
                    fileData = new File(path + uploadedFile.getOriginalFilename());
                    files.add(fileData);
                    uploadedFile.transferTo(fileData);
                    urls.add(fileData.getAbsoluteFile());
                }
            }
            status = HttpStatus.OK;
            map.put("status", 200);
            map.put("message", "Uploaded successfully");
            map.put("url", urls);
        } catch (Exception exception) {
            System.out.println("@uploadFiles Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "uploadFiles", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> createCase(String formId, String caseId) {
        Map map = new HashMap();
        HttpStatus status = null;
        JSONParser jsonParser = new JSONParser();
        try {
            IncompleteForm incompleteForm = incompleteFormRepository.findById(Integer.parseInt(formId)).get();
            if (incompleteForm != null) {
                JSONObject jsonObject = new JSONObject();
                JSONObject extractedData = (JSONObject) jsonParser.parse(incompleteForm.getFormData());
                Cases cases = new Cases();
                BasicDoctorInfo basicDoctorInfo = new BasicDoctorInfo();
                BasicPatientInfo basicPatientInfo = new BasicPatientInfo();
                incompleteForm.setFlag("N");
                cases.setCaseId(caseId);
                //cases.setTreatmentLink("e");
                //cases.setDownloadLink("e");
                cases.setFormData(incompleteForm.getFormData());
                cases.setSubmittedBy(incompleteForm.getSubmittedBy());
                cases.setRemarks(incompleteForm.getRemarks());
                cases.setPlanStatus("Select");
                cases.setTermConditionStatus(0);
                cases.setDoctorName(extractedData.get("DoctorName").toString());
                cases.setGroupId(incompleteForm.getGroupId());
                basicDoctorInfo.setCaseId(caseId);
                basicDoctorInfo.setDoctorName(extractedData.get("user").toString());
                basicDoctorInfo.setPhoneNumber(extractedData.get("DoctorPhoneNumber").toString());
                basicDoctorInfo.setTreatingDoctor(extractedData.get("treatingDoctor").toString());
                basicDoctorInfo.setTreatingDoctorPhoneNumber(extractedData.get("treatingDoctorPhone").toString());
                basicDoctorInfo.setClinicAddress(extractedData.get("ClinicAddress").toString());
                basicDoctorInfo.setCity(extractedData.get("City").toString());
                basicDoctorInfo.setClinicEmail(extractedData.get("ClinicEmail").toString());
                basicPatientInfo.setCaseId(caseId);
                basicPatientInfo.setPatientName(extractedData.get("PatientName").toString());
                basicPatientInfo.setGender(extractedData.get("Gender").toString());
                basicPatientInfo.setDob(extractedData.get("DOB").toString());
                basicPatientInfo.setAge(extractedData.get("Age").toString());
                basicPatientInfo.setPatientCategory(extractedData.get("patientCategoryCode").toString());
                basicPatientInfo.setChiefComplaint(extractedData.get("ChiefComplaint").toString());
                incompleteFormRepository.saveAndFlush(incompleteForm);
                caseRepository.saveAndFlush(cases);
                basicDoctorInfoRepository.saveAndFlush(basicDoctorInfo);
                basicPatientInfoRepository.saveAndFlush(basicPatientInfo);
                map.put("status", 200);
                map.put("message", "Data Saved");
                map.put("data", cases);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Id not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@createCase Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "createCase", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<Map> getVideos(String caseId, int planNo) {
        Map map = new HashMap();
        List list = new ArrayList();
        HttpStatus status = null;
        try {
            String folderName = "";
            if (planNo != 1)
                folderName = caseId + "-" + planNo;
            else
                folderName = caseId;
            String folderPath = environment.getProperty("videos.file.path") + folderName + File.separator + "Videos" + File.separator;
            System.out.println("folderPath::"+folderPath);
            File file = new File(folderPath);
            if (file.exists()) {
                File videosList[] = file.listFiles();
                for (File video : videosList) {
                    Path filePath = Paths.get(video.getAbsolutePath());
                    byte[] arr = Files.readAllBytes(filePath);
                    Map videoData = new HashMap();
                    videoData.put("filename", video.getName());
                    videoData.put("byteArray", arr);
                    list.add(videoData);
                }
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", 404);
                map.put("message", "Folder is not present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getVideos Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getVideos", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

}
