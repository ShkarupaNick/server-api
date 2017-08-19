package com.couchtalks.service.impl;

import com.couchtalks.dao.web.LikesDao;
import com.couchtalks.entity.web.Likes;
import com.couchtalks.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by NShkarupa on 09.08.2017.
 */
@Service
public class LikeServiceImpl implements LikesService {

    @Autowired
    LikesDao likesDao;

    @Override
    public Likes findOne(UUID commentUUID, Long userId) {
        return likesDao.findOne(commentUUID, userId);
    }

    @Override
    public void save(Likes likes) {
        likesDao.save(likes);
    }

    @Override
    public void delete(Likes likes) {
        likesDao.delete(likes);
    }
}
