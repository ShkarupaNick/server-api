package com.couchtalks.service;

import com.couchtalks.entity.web.Likes;

import java.util.UUID;

/**
 * Created by NShkarupa on 09.08.2017.
 */


public interface LikesService {
    public Likes findOne(UUID commentUUID, Long userId);

    public void save(Likes likes);
    public void delete(Likes likes);
}
