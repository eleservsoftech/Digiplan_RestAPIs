package com.digiplan.repositories;

import com.digiplan.entities.Chatter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.digiplan.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByCaseId(String caseId);

    @Query(value = "CALL GetCaseByStage(:stageName)", nativeQuery = true)
    List<Object[]> getCommentsByStage(@Param("stageName") String stageName);
}

