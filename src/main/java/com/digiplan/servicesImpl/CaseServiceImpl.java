package com.digiplan.servicesImpl;

import com.digiplan.entities.BasicDoctorInfo;
import com.digiplan.entities.BasicPatientInfo;
import com.digiplan.entities.Cases;
import com.digiplan.entities.IncompleteForm;
import com.digiplan.entities.Logger;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    public String ApiService(RestTemplate restTemplate) {
        return (this.restTemplate = restTemplate).toString();
    }

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

    public  ResponseEntity<Map> getCaseId(String caseId){
        HttpStatus status =null;
        Map<Object,Object>  map = new HashMap<>();
        try {
            Cases caseIdData = this.caseRepository.findByCaseId(caseId);
            if (caseIdData!=null){
                map.put("status_code", "200");
                map.put("results", this.caseRepository.findByCaseId(caseId));
                map.put("message", "Case Id "+caseId+" Found");
                status = HttpStatus.OK;
            } else {
                map.put("status_code", "404");
                map.put("results", this.caseRepository.findByCaseId(caseId));
                map.put("errorMessage", "Case Id "+caseId+" Not Found!");
                status = HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            map.put("status_code", "500");
            map.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  new ResponseEntity<>(map,status);
    }



//    public Cases addCase(Cases casesData) {
//        Cases cases =  null;
//        try {
//
//            cases = (Cases)this.caseRepository.saveAndFlush(casesData);
//        } catch (Exception exception) {
//            System.out.println("@addCase Exception : " + exception);
//            Logger logger = new Logger(this.utilityService.getLoggerCorrelationId(), "addCase", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            this.loggerRepository.saveAndFlush(logger);
//        }
//        return cases;
//    }

    public ResponseEntity<Map> addCase(Cases casesData) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        Cases casesEntity = new Cases();
        try {
            casesEntity = new Cases();
            if (casesData.getCaseId()!=null && !casesData.getCaseId().isEmpty()){
            casesEntity.setId(casesData.getId());
            casesEntity.setCaseId(casesData.getCaseId());
            casesEntity.setSubmittedOn(casesData.getSubmittedOn());
            casesEntity.setTreatmentLink(casesData.getTreatmentLink());
            casesEntity.setDownloadLink(casesData.getDownloadLink());
            casesEntity.setFormData(casesData.getFormData());
            casesEntity.setSubmittedBy(casesData.getSubmittedBy());
            casesEntity.setRemarks(casesData.getRemarks());
            casesEntity.setPlanStatus(casesData.getPlanStatus());
            casesEntity.setTermConditionStatus(casesData.getTermConditionStatus());
            casesEntity.setDoctorName(casesData.getDoctorName());
            casesEntity.setGroupId(casesData.getGroupId());
            this.caseRepository.saveAndFlush(casesEntity);
            map.put("status_code", "201");
            map.put("message", "Data saved successfully");
            map.put("data", casesEntity);
            status = HttpStatus.CREATED;
            }else{
                map.put("status_code", "400");
                map.put("message", "Case Id is mandatory");
                status = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            map.put("status_code", "500");
            map.put("message", ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.info("service log  addUsers{} " + ex.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    public ResponseEntity<Map> myCases(String username) {
        Map<Object, Object> map = new HashMap<>();
        HttpStatus status = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject extractedData;
        try {
            List<Cases> casesList = this.caseRepository.getCasesByUserName(username);
            for (Cases cases : casesList) {
                if (cases.getFormData() != null && !cases.getFormData().isEmpty()) {
                    extractedData = (JSONObject) jsonParser.parse(cases.getFormData());
                }
            }
            if (casesList.isEmpty()) {
                map.put("status", 404);
                map.put("message", "No data found");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", 200);
                map.put("message", "OK");
                map.put("data", casesList);
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
                responseEntity = ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[] { "attachment; filename=" + caseId + "" })).contentLength(file.length()).contentType(MediaType.APPLICATION_PDF).body(inputStreamResource);
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
                    String planstatus = cases.getPlanStatus();
                    System.out.println("planstatus: "+planstatus);
//                    //
//                    String caseId = cases.getCaseId();
//                    System.out.println("digi caseId: "+caseId);
//                    // Assuming you have the caseId from some part of your logic
//                    //String apiUrl = "http://localhost:9096/serveImage/" + caseId;
//                    String apiUrl = "http://localhost:8080/serveImage/"+caseId;
//                    System.out.println("digi api url: "+apiUrl);
//                    ResponseEntity<Resource> responseEntity = restTemplate.exchange(
//                            apiUrl,
//                            HttpMethod.GET,
//                            null,
//                            new ParameterizedTypeReference<Resource>() {} // Use the appropriate type for Resource
//                    );
//                    cases.setPatientPhoto((MultipartFile) responseEntity);
//                    //
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
            exception.printStackTrace();
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }


    // Your corrected API

}
