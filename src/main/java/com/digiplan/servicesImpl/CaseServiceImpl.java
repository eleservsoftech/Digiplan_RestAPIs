package com.digiplan.servicesImpl;

import com.digiplan.entities.BasicDoctorInfo;
import com.digiplan.entities.BasicPatientInfo;
import com.digiplan.entities.Cases;
import com.digiplan.entities.IncompleteForm;
import com.digiplan.entities.Logger;
import com.digiplan.entities.User;
import com.digiplan.repositories.BasicDoctorInfoRepository;
import com.digiplan.repositories.BasicPatientInfoRepository;
import com.digiplan.repositories.CaseRepository;
import com.digiplan.repositories.IncompleteFormRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.repositories.UserRepository;
import com.digiplan.services.CaseService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
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

    public List<Cases> getAllCases() {
        List<Cases> casesList = null;
        try {
            casesList = this.caseRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllCases Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "getAllCases", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
        }
        return casesList;
    }

    public Cases addCase(Cases casesData) {
        Cases cases = null;
        try {
            cases = (Cases)this.caseRepository.saveAndFlush(casesData);
        } catch (Exception exception) {
            System.out.println("@addCase Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "addCase", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
        }
        return cases;
    }

    public ResponseEntity<Map> myCases(String username) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        List<String> userList_username = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        Boolean doctorAdminCheck = Boolean.valueOf(false);
        try {
            List<User> userList = this.userRepository.findAllUsersList(username);
            List<Cases> casesList = this.caseRepository.findAll();
            if (!userList.isEmpty() || userList != null)
                doctorAdminCheck = Boolean.valueOf(true);
            for (User user : userList)
                userList_username.add(user.getUsername());
            for (Cases cases : casesList) {
                if (cases.getFormData() != null && cases.getFormData() != "") {
                    JSONObject jsonObject = new JSONObject();
                    JSONObject extractedData = (JSONObject)jsonParser.parse(cases.getFormData());
                    String casesList_username = extractedData.get("user").toString();
                    if (casesList_username != null && (casesList_username.equals(username) || (doctorAdminCheck.booleanValue() && userList_username.contains(casesList_username)))) {
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
                map.put("status", Integer.valueOf(404));
                map.put("message", "No data found");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", Integer.valueOf(200));
                map.put("message", "OK");
                map.put("data", jsonArray);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("@myCases Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "myCases", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
            map.put("status", Integer.valueOf(500));
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Object> downloadReport(String caseId) {
        ResponseEntity<Object> responseEntity = null;
        try {
            String reportPath = this.environment.getProperty("report.download.path") + caseId + "/Report.pdf";
            File file = new File(reportPath);
            if (file.exists()) {
                InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
                responseEntity = ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[] { "attachment; filename=" + file.getName() + "" })).contentLength(file.length()).contentType(MediaType.APPLICATION_PDF).body(inputStreamResource);
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("file not found!");
            }
        } catch (Exception exception) {
            System.out.println("@downloadReport Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "downloadReport", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
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
        Map<String, Object> map = new HashMap<>();
        List<File> urls = new ArrayList();
        try {
            List<File> files = new ArrayList<>();
            String path = this.environment.getProperty("upload.file.path");
            File fileData = null;
            for (MultipartFile uploadedFile : file) {
                if (!uploadedFile.isEmpty() && uploadedFile.getSize() != 0L) {
                    fileData = new File(path + uploadedFile.getOriginalFilename());
                    files.add(fileData);
                    uploadedFile.transferTo(fileData);
                    urls.add(fileData.getAbsoluteFile());
                }
            }
            status = HttpStatus.OK;
            map.put("status", Integer.valueOf(200));
            map.put("message", "Uploaded successfully");
            map.put("url", urls);
        } catch (Exception exception) {
            System.out.println("@uploadFiles Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "uploadFiles", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
            map.put("status", Integer.valueOf(500));
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> createCase(String formId, String caseId) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        JSONParser jsonParser = new JSONParser();
        try {
            IncompleteForm incompleteForm = this.incompleteFormRepository.findById(Integer.valueOf(Integer.parseInt(formId))).get();
            if (incompleteForm != null) {
                JSONObject jsonObject = new JSONObject();
                JSONObject extractedData = (JSONObject)jsonParser.parse(incompleteForm.getFormData());
                Cases cases = new Cases();
                BasicDoctorInfo basicDoctorInfo = new BasicDoctorInfo();
                BasicPatientInfo basicPatientInfo = new BasicPatientInfo();
                incompleteForm.setFlag("N");
                cases.setCaseId(caseId);
                cases.setFormData(incompleteForm.getFormData());
                cases.setSubmittedBy(incompleteForm.getSubmittedBy());
                cases.setRemarks(incompleteForm.getRemarks());
                cases.setPlanStatus("Select");
                cases.setTermConditionStatus(Integer.valueOf(0));
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
                this.incompleteFormRepository.saveAndFlush(incompleteForm);
                this.caseRepository.saveAndFlush(cases);
                this.basicDoctorInfoRepository.saveAndFlush(basicDoctorInfo);
                this.basicPatientInfoRepository.saveAndFlush(basicPatientInfo);
                map.put("status", Integer.valueOf(200));
                map.put("message", "Data Saved");
                map.put("data", cases);
                status = HttpStatus.OK;
            } else {
                map.put("status", Integer.valueOf(404));
                map.put("message", "Id not found");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@createCase Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "createCase", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
            map.put("status", Integer.valueOf(500));
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> getVideos(String caseId, int planNo) {
        Map<Object, Object> map = new HashMap<>();
        List<Map<Object, Object>> list = new ArrayList();
        HttpStatus status = null;
        try {
            String folderName = "";
            if (planNo != 1) {
                folderName = caseId + "-" + planNo;
            } else {
                folderName = caseId;
            }
            String folderPath = this.environment.getProperty("videos.file.path") + folderName + File.separator + "Videos" + File.separator;
            System.out.println("folderPath::" + folderPath);
            File file = new File(folderPath);
            if (file.exists()) {
                File[] videosList = file.listFiles();
                for (File video : videosList) {
                    Path filePath = Paths.get(video.getAbsolutePath(), new String[0]);
                    byte[] arr = Files.readAllBytes(filePath);
                    Map<Object, Object> videoData = new HashMap<>();
                    videoData.put("filename", video.getName());
                    videoData.put("byteArray", arr);
                    list.add(videoData);
                }
                map.put("status", Integer.valueOf(200));
                map.put("message", "OK");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", Integer.valueOf(404));
                map.put("message", "Folder is not present");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getVideos Exception : " + exception);
            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "getVideos", exception.getMessage(), exception.toString(), LocalDateTime.now());
            this.loggerRepository.saveAndFlush(logger);
            map.put("status", Integer.valueOf(500));
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(map, status);
    }

    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    public String ApiService(RestTemplate restTemplate) {
       return (this.restTemplate = restTemplate).toString();
    }


    public ResponseEntity<Map> getUserData(String userId) {
        Map<String, Object> map = new HashMap<>();
        HttpStatus status;
        JSONParser jsonParser = new JSONParser();
        JSONObject extractedData;
        String baseURL = this.environment.getProperty("patient.profile.photo");
        try {
            List<Cases> casesList = this.caseRepository.getCasesByUserName(userId);
            for (Cases cases : casesList) {
                try {
                    //this piece of code will invoking QRCodeGenerator api to get the latest photo of a particular patient,assuming that case is mapped in Digiplan.
                    ResponseEntity<byte[]> imageResponse = restTemplate.getForEntity(baseURL + cases.getCaseId(), byte[].class);
                   //ResponseEntity<byte[]> imageResponse = restTemplate.getForEntity(baseURL+"5000000000" , byte[].class); // this is for testing purpose,it's working
                    if (imageResponse.getStatusCode() == HttpStatus.OK) {
                        byte[] imageBytes = imageResponse.getBody();
                        cases.setImage(imageBytes);
                    }
                    extractedData = (JSONObject) jsonParser.parse(cases.getFormData());
                } catch (HttpServerErrorException.InternalServerError e) {
                    log.info("Server encountered an internal error: " + e.getMessage());
                } catch (Exception e) {
                    log.info("Other exception occurred: " + e.getMessage());
                }
            }
            if (!casesList.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", 200);
                map.put("message", "Data Found!");
                map.put("data", casesList);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", 404);
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }
}
