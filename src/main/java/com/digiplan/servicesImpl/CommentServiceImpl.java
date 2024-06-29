package com.digiplan.servicesImpl;

import com.digiplan.entities.Cases;
import com.digiplan.entities.Comment;
import com.digiplan.entities.Logger;
import com.digiplan.repositories.CaseRepository;
import com.digiplan.repositories.CommentRepository;
import com.digiplan.repositories.LoggerRepository;
import com.digiplan.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {



    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public ResponseEntity<Map> getComment(String caseId) {
        List<Comment> list = null;
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
            list = commentRepository.findByCaseId(caseId);
            if (list.isEmpty()) {
                map.put("status", "404");
                map.put("message", "No Data Found");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            } else {
                map.put("status", "200");
                map.put("message", "Data Found");
                map.put("data", list);
                status = HttpStatus.OK;
            }
        } catch (Exception exception) {
            System.out.println("@getComment Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getComment", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> commentsList = null;
        try {
            commentsList = commentRepository.findAll();
        } catch (Exception exception) {
            System.out.println("@getAllComments Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getAllComments", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return commentsList;
    }

    @Override
    public ResponseEntity<Map> addComment(Comment commentData, String commentType) {
        Comment comment = null;
        Map<String, Object> map = new HashMap();
        HttpStatus status = null;
        try {
//            if (commentType.equals("Plan Approved")) {
//                Cases cases = caseRepository.getById(commentData.getCaseId());
//                cases.setPlanStatus(commentData.getStage());
//                cases.setTermConditionStatus(1);
//                caseRepository.saveAndFlush(cases);
//            }
            comment = commentRepository.saveAndFlush(commentData);
            map.put("status", "201");
            map.put("message", "Data Saved");
            status = HttpStatus.OK;
        } catch (Exception exception) {
            System.out.println("@addComment Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "addComment", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }

//    @Override
//    public Comment updateComment(Integer id, Comment commentData) {
//        Comment comment = null;
//        try {
//            Optional<Comment> check = commentRepository.findById(id);
//            System.out.println("Reached here1");
//            if (check.isPresent()) {
//                System.out.println("Reached here");
//                commentData.setId(id);
//                comment = commentRepository.saveAndFlush(commentData);
//                System.out.println("Reached here2");
//            }
//        } catch (Exception exception) {
//            System.out.println("@updateComment Exception : " + exception);
//            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateComment", exception.getMessage(), exception.toString(), LocalDateTime.now());
//            loggerRepository.saveAndFlush(logger);
//        }
//        return comment;
//    }

    @Override
    public Comment updateComment(Integer id, Comment commentData) {
        Comment updatedComment = null;
        try {
            Optional<Comment> existingCommentOpt = commentRepository.findById(id);
            if (existingCommentOpt.isPresent()) {
                Comment existingComment = existingCommentOpt.get();

                // Update only the fields that are provided in commentData
                if (commentData.getCaseId() != null) {
                    existingComment.setCaseId(commentData.getCaseId());
                }
                if (commentData.getUsername() != null) {
                    existingComment.setUsername(commentData.getUsername());
                }
                if (commentData.getStage() != null) {
                    existingComment.setStage(commentData.getStage());
                }
                if (commentData.getComment() != null) {
                    existingComment.setComment(commentData.getComment());
                }
                if (commentData.getPlanNo() != null) {
                    existingComment.setPlanNo(commentData.getPlanNo());
                }
                if (commentData.getDate() != null) {
                    existingComment.setDate(commentData.getDate());
                }
                if (commentData.isSupportAction()) {
                    existingComment.setSupportAction(commentData.isSupportAction());
                }

                // Save the updated entity
                updatedComment = commentRepository.saveAndFlush(existingComment);
            }
        } catch (Exception exception) {
            System.out.println("@updateComment Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "updateComment", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return updatedComment;
    }




    @Override
    public String deleteComment(Integer id) {
        String status = "";
        try {
            commentRepository.deleteById(id);
            status = "Deleted";
        } catch (Exception exception) {
            System.out.println("@deleteComment Exception : " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "deleteComment", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
        }
        return status;
    }

    @Override
    public ResponseEntity<Map> getCommentTypes(String caseId, String typeOfUser) {
        Map<String, Object> map = new HashMap();
        List list = new ArrayList();
        HttpStatus status = null;
        try {
            Optional<Cases> object = caseRepository.findById(caseId);
            if (object.isPresent()) {
                Cases cases = object.get();
                if (cases.getPlanStatus().equalsIgnoreCase("Pending sign off")) {
                    if (typeOfUser.equalsIgnoreCase("support") || typeOfUser.equalsIgnoreCase("caseuser")) {
                        list.add(addValueInMap(1, "Pending sign off", true));
                    } else {
                        list.add(addValueInMap(1, "Pending sign off", true));
                        list.add(addValueInMap(2, "Plan Approved", false));
                        list.add(addValueInMap(3, "Required Modification", false));
                    }
                } else if (cases.getPlanStatus().equalsIgnoreCase("Required Modification")) {
                    if (typeOfUser.equalsIgnoreCase("support") || typeOfUser.equalsIgnoreCase("caseuser")) {
                        list.add(addValueInMap(1, "Required Modification", true));
                        list.add(addValueInMap(2, "Pending sign off", false));
                    } else {
                        list.add(addValueInMap(3, "Required Modification", true));
                    }
                } else if (cases.getPlanStatus().equalsIgnoreCase("Plan Approved")) {
                    list.add(addValueInMap(1, "Plan Approved1", true));
                } else {
                    list.add(addValueInMap(1, cases.getPlanStatus(), true));
                    list.add(addValueInMap(2, "Pending sign off", false));
                    if (!typeOfUser.equalsIgnoreCase("Support")) {
                        list.add(addValueInMap(3, "Plan Approved", false));
                    }
                    list.add(addValueInMap(4, "Required Modification", false));
                }
                map.put("status", 200);
                map.put("message", "Data Found");
                map.put("data", list);
                status = HttpStatus.OK;
            } else {
                map.put("status", "404");
                map.put("message", "No Data Found");
                map.put("data", "");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            System.out.println("@getCommentTypes Exception = " + exception);
            Logger logger = new Logger(utilityService.getLoggerCorrelationId(), "getCommentTypes", exception.getMessage(), exception.toString(), LocalDateTime.now());
            loggerRepository.saveAndFlush(logger);
            map.put("status", 500);
            map.put("message", "Internal Server Error");
            map.put("error", exception.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(map, status);
    }



    private Map<String, Object> addValueInMap(int id, String planStatus, boolean status) {
        Map<String, Object> map = new HashMap();
        map.put("id", id);
        map.put("planStatus", planStatus);
        map.put("status", status);
        return map;
    }


//    public List<Comment> findByStage(String stage) {
//        return commentRepository.findByStage(stage);
//    }


    @Override
    public List<Comment> getCommentsByStage(String stageName) {
        List<Object[]> result = commentRepository.getCommentsByStage(stageName);
        List<Comment> comments = new ArrayList<>();

        for (Object[] obj : result) {
            Comment comment = new Comment();
            comment.setId((Integer) obj[0]);           // Assuming comment_id is returned as Integer
            comment.setCaseId((String) obj[1]);
            comment.setPatient_Name((String) obj[2]);
            comment.setDoctor_Name((String) obj[3]);
            comment.setStage((String) obj[4]);         // Ensure stage is set
            comment.setUsername((String) obj[5]);
            comment.setPlanNo((String) obj[6]);
            comment.setComment((String) obj[7]);


            comments.add(comment);
        }

        return comments;
    }




}
