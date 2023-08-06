package com.digiplan.services;

import com.digiplan.entities.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CommentService {

    ResponseEntity<Map> getComment(String caseId);

    List<Comment> getAllComments();

    ResponseEntity<Map> addComment(Comment commentData, String commentType);

    Comment updateComment(Integer id, Comment commentData);

    String deleteComment(Integer id);

    ResponseEntity<Map> getCommentTypes(String caseId, String typeOfUser);
}
