package com.couchtalks.service;

import com.couchtalks.entity.web.Comment;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 09.04.2017.
 */
public interface CommentService {

    public List<Comment> findAll();
    public List<Comment> findAllByEntityId(String entityId);
    public List<Comment> findRootEntityId(String entityId);
    public List<Comment> findTopComments(String entityId, Integer entityCnt);
    public List<Comment> findTopComments(UUID entityId, Integer entityCnt);
    public Comment saveComment(Comment comment);
    public void deleteComment(Comment comment);
    public void modifyComment(Comment comment);
    public Comment like(UUID uuid, Long userId);
    public Comment disLike(UUID uuid, Long userId);
    public Comment findOne(UUID uuid);



}
