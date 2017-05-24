package com.couchtalks.entity.web;

import com.couchtalks.entity.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ShkarupaN on 08.04.2017.
 */
@javax.persistence.Entity
public class Comment extends Entity {

    @ManyToOne
    @JoinColumn(name = "parent_comment_uuid")
    @JsonProperty
    @JsonIgnoreProperties({"item","likeCnt", "reportCnt", "deleted", "text", "date"})

    Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "item_uuid")
    @JsonProperty
    @JsonIgnoreProperties({"url","name","season","number","airdate","airtime","airstamp","runtime","image","summary","show","_links","is_featured", "categories"})
    private Item item;


    @Column(name = "like_count")
    private Integer likeCnt=0;

    @Column(name = "report_count")
    private Integer reportCnt=0;


    private boolean deleted=false;


    private String text;

    @CreatedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:sss", timezone = "Europe/Kiev")
    private Date date;


    public Integer getReportCnt() {
        return reportCnt;
    }

    public void setReportCnt(Integer reportCnt) {
        this.reportCnt = reportCnt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Integer getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public void plusLikeCnt() {
        this.likeCnt++;
    }

    public void minusLikeCnt() {
        this.likeCnt--;
    }

    public void plusReportCnt() {
        this.reportCnt++;
    }

    public void minusReportCnt() {
        this.reportCnt--;
    }


    @PrePersist
    private void onPrePersist(){
        if(date==null){
            date=new Date();
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", item=" + item +
                ", likeCnt=" + likeCnt +
                ", reportCnt=" + reportCnt +
                ", deleted=" + deleted +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
