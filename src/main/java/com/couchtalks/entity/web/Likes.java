package com.couchtalks.entity.web;

import com.couchtalks.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ShkarupaN on 08.04.2017.
 */

@Entity
@Table(name = "likes")
@Where(clause = "deleted='false'")

public class Likes extends com.couchtalks.entity.web.Entity {

    @ManyToOne
    private Comment comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
