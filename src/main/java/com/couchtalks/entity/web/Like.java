package com.couchtalks.entity.web;

import com.couchtalks.entity.User;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ShkarupaN on 08.04.2017.
 */

@Entity
@Table(name = "likes")

public class Like  extends com.couchtalks.entity.web.Entity{


    @ManyToOne
    private Comment comment;

    @PrePersist
    protected void onCreate() {
       comment.plusLikeCnt();
    }
    @PreRemove
    protected void onRemove() {
        comment.minusLikeCnt();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
