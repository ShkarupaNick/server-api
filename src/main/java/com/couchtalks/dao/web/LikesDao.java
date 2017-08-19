package com.couchtalks.dao.web;

import com.couchtalks.entity.web.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Created by NShkarupa on 09.08.2017.
 */
public interface LikesDao extends JpaRepository<Likes, Long> {
    @Query(value = "SELECT l FROM Likes l WHERE l.comment.uuid=:commentUUID and l.user.id=:userId")
    public Likes findOne(@Param("commentUUID") UUID commentUUID, @Param("userId") Long userId);

    @Query(value = "delete from Likes l where l.uuid=:likesUUID")
    public Likes delete(@Param("likesUUID") UUID likesUUID);

}