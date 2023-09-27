package com.digiplan.controllers;

import com.digiplan.entities.Cases;
import com.digiplan.entities.User;
import com.digiplan.repositories.CaseRepository;
import com.digiplan.services.CaseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@CrossOrigin(origins = {"*"})
@RestController
public class CaseController {
    @Autowired
    private CaseService caseService;

    @Autowired
    private Environment environment;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping({"/getAllCases"})
    public Map<String, Object> getAllCases() {
        Map<String, Object> map = new HashMap<>();
        try {
            if (this.caseService.getAllCases().size() > 0) {
                map.put("status_code", "200");
                map.put("results", this.caseService.getAllCases());
                map.put("message", "Success");
            } else {
                map.put("status_code", "204");
                map.put("results", "No Content!");
                map.put("message", "Records Not Found!");
            }
        } catch (Exception e) {
            map.put("status_code", "500");
            map.put("results", "Internal Server Error!");
            map.put("message", e.getMessage());
        }
        return map;
    }

    @PostMapping({"/savecase"})
    public ResponseEntity<Map> addCase(@Valid @RequestBody Cases casesData) {
        return this.caseService.addCase(casesData);
    }

    @PostMapping({"/mycase"})
    public ResponseEntity<Map> myCases(@RequestBody User userData) {
        return this.caseService.myCases(userData.getUsername());
    }

    @GetMapping({"/users/{caseId}/Report.pdf"})
    public ResponseEntity<Object> downloadReport(@PathVariable String caseId) {
        return this.caseService.downloadReport(caseId);
    }

    @PostMapping({"/getCaseDetails"})
    public ResponseEntity<Map> getCaseDetails(@RequestParam String caseId) {
        return this.caseService.getCaseDetails(caseId);
    }

    @GetMapping({"/getCase/{caseId}"})
    public ResponseEntity<Map> getCaseyId(@PathVariable String caseId) {
        return this.caseService.getCaseId(caseId);
    }

    @PostMapping({"/uploadFiles"})
    public ResponseEntity<Map> uploadFiles(@RequestParam List<MultipartFile> file) {
        return this.caseService.uploadFiles(file);
    }

    @PostMapping({"/createCase"})
    public ResponseEntity<Map> createCase(@RequestParam String formId, @RequestParam String caseId) {
        return this.caseService.createCase(formId, caseId);
    }

    @PostMapping({"/getVideos"})
    public ResponseEntity<Map> getVideos(@RequestParam String caseId, @RequestParam int planNo) {
        return this.caseService.getVideos(caseId, planNo);
    }

    @GetMapping({"/GetUserCases/{userId}"})
    public ResponseEntity<Map<String, Object>> getUserData(@PathVariable String userId) {
        HttpStatus status;
        Map<String, Object> map = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        String baseURL = this.environment.getProperty("patient.profile.photo");
        try {
            List<Cases> casesList = this.caseRepository.getCasesByUserName(userId);
            for (Cases cases : casesList) {
                try {
                    if(cases.getPlanStatus().equalsIgnoreCase("Select")){
                        cases.setPlanStatus("");
                    }
                      String caseId = cases.getCaseId();
//                    String apiUrl = "http://localhost:8080/serveImage/" + caseId;
//                    ResponseEntity<Resource> responseEntity = this.restTemplate.exchange(apiUrl, HttpMethod.GET, null, Resource.class, new Object[0]);
//                    cases.setPatientPhoto((MultipartFile)responseEntity.getBody());
                    JSONObject jSONObject = (JSONObject)jsonParser.parse(cases.getFormData());
                } catch (org.springframework.web.client.HttpServerErrorException.InternalServerError e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.getMessage();
                    System.out.println("Other exception occurred: " + e.getMessage());
                }
            }
            if (!casesList.isEmpty()) {
                status = HttpStatus.OK;
                map.put("status", Integer.valueOf(200));
                map.put("message", "Data Found!");
                map.put("data", casesList);
            } else {
                status = HttpStatus.NOT_FOUND;
                map.put("status", Integer.valueOf(404));
                map.put("message", "No Data Found");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            map.put("status", Integer.valueOf(500));
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
        }
        return new ResponseEntity(map, status);
    }

    @Autowired
    public String ApiService(RestTemplate restTemplate) {
        return (this.restTemplate = restTemplate).toString();
    }

    @GetMapping({"/patientPhoto/{caseId}"})
    public ResponseEntity<Resource> serveImage(@PathVariable String caseId) {
        String baseURL = this.environment.getProperty("patient.photo");
        ResponseEntity<Resource> responseEntity = this.restTemplate.getForEntity(baseURL + caseId, Resource.class, new Object[0]);
        return responseEntity;
    }

}
