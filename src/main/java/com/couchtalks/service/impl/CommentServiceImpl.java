package com.couchtalks.service.impl;

import com.couchtalks.dao.web.CommentDao;
import com.couchtalks.entity.web.Comment;
import com.couchtalks.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 09.04.2017.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public List<Comment> findAllByEntityId(String entityId) {
        return commentDao.findByEntityId(UUID.fromString(entityId));
    }

    @Override
    public List<Comment> findTopComments(String entityId, Integer entityCnt) {
        return commentDao.findTopByEntityId(UUID.fromString(entityId), new PageRequest(0,entityCnt));
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public void modifyComment(Comment comment) {
        //TODO modify comment
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }
}
