package com.couchtalks.entity.web;

import javax.persistence.*;

/**
 * Created by ShkarupaN on 09.04.2017.
 */

@javax.persistence.Entity
public class Reported extends Entity{
    @ManyToOne
    private Comment comment;

    @PrePersist
    protected void onCreate() {
        comment.plusReportCnt();
    }
    @PreRemove
    protected void onRemove() {
        comment.minusReportCnt();
    }
}
