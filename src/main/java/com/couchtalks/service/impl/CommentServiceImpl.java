package com.couchtalks.service.impl;

import com.couchtalks.dao.web.CommentDao;
import com.couchtalks.entity.User;
import com.couchtalks.entity.web.Comment;
import com.couchtalks.entity.web.Likes;
import com.couchtalks.service.CommentService;
import com.couchtalks.service.LikesService;
import com.couchtalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by ShkarupaN on 09.04.2017.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private LikesService likesService;

    @Override
    public List<Comment> findAllByEntityId(String entityId) {
        return commentDao.findByEntityId(UUID.fromString(entityId));
    }

    @Override
    public List<Comment> findRootEntityId(String entityId) {
        return commentDao.findRootEntityId(UUID.fromString(entityId));
    }


    @Override
    public List<Comment> findTopComments(String entityId, Integer entityCnt) {
        return commentDao.findTopByEntityId(UUID.fromString(entityId), new PageRequest(0, entityCnt));
    }

    @Override
    public List<Comment> findTopComments(UUID entityId, Integer entityCnt) {
        return commentDao.findTopByEntityId(entityId, new PageRequest(0, entityCnt));
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentDao.save(comment);
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
    @Transactional
    public Comment like(UUID uuid, Long userId) {
        Comment comment = commentDao.findByUUID(uuid);
        User user = userService.findOne(userId);
        Likes likes = likesService.findOne(uuid, userId);
        if (null == likes) {
            likes = new Likes();
            likes.setUser(user);
            comment.addLikes(likes);
            comment.plusLikeCnt();
            commentDao.save(comment);
        }
        return comment;
    }

    @Override
    @Transactional
    public Comment disLike(UUID uuid, Long userId) {
        Comment comment = commentDao.findByUUID(uuid);
        Likes likes = likesService.findOne(uuid, userId);
        if (null != likes) {
            comment.minusLikeCnt();
            comment.deleteLike(likes);
            likesService.delete(likes);
            commentDao.save(comment);
        }
        return comment;
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment findOne(UUID uuid){
        return commentDao.findByUUID(uuid);
    }
}
