package com.couchtalks.service;

import com.couchtalks.entity.web.Comment;

import java.util.List;

/**
 * Created by ShkarupaN on 09.04.2017.
 */
public interface CommentService {
    public List<Comment> findAllByEntityId(String entityId);
    public List<Comment> findTopComments(String entityId, Integer entityCnt);
    public void saveComment(Comment comment);
    public void deleteComment(Comment comment);
    public void modifyComment(Comment comment);

    public List<Comment> findAll();
}
