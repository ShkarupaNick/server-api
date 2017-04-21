package com.couchtalks.dao.web;

import com.couchtalks.entity.web.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 09.04.2017.
 */
public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.item.uuid = :entityUUID")
    public List<Comment> findByEntityId(@Param("entityUUID") UUID entityUUID);


    @Query("SELECT c  FROM Comment c WHERE c.item.uuid = :entityUUID order by c.likeCnt desc")
    public List<Comment> findTopByEntityId(@Param("entityUUID") UUID entityUUID, Pageable pageable);




}
