package com.couchtalks.entity.web;

import com.couchtalks.entity.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ShkarupaN on 08.04.2017.
 */
@javax.persistence.Entity
public class Comment extends Entity {

    /*@ManyToOne
    @JoinColumn(name = "parent_comment_uuid")
    @JsonProperty
    @JsonIgnoreProperties({"item", "likeCnt", "reportCnt", "deleted", "text", "date"})
    Comment parentComment;


    @OneToMany(mappedBy = "uuid", fetch = FetchType.EAGER)
    Set<Comment> childComments = new HashSet<Comment>();*/

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_uuid", referencedColumnName = "uuid")
    @JsonProperty
    @JsonIgnoreProperties({"uuid","user", "children", "parent", "createdDate", "lastModifiedDate", "version", "deleted", "item", "likeCnt", "reportCnt", "likes", "date", "likedByCurrentUser"})
    public Comment parent;


    @OrderColumn(name = "position")
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_uuid")
    public List<Comment> children = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "item_uuid")
    @JsonProperty
    @JsonIgnoreProperties({"url", "name", "season", "number", "airdate", "airtime", "airstamp", "runtime", "image", "summary", "show", "_links", "is_featured", "categories"})
    private Item item;


    @Column(name = "like_count")
    private Integer likeCnt = 0;

    @Column(name = "report_count")
    private Integer reportCnt = 0;


    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_uuid")
    public List<Likes> likes;

    private String text;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:sss", timezone = "Europe/Kiev")
    private Date date;

    @Transient
    @JsonIgnore
    private boolean isLikedByCurrentUser;


    public void addChildren(Comment childComment){
        this.getChildren().add(childComment);
    }

    public void deleteLike(Likes like){
        likes.remove(like);
    }

    public void addLike(Likes like){
        likes.add(like);
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public boolean isLikedByCurrentUser() {
        return isLikedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        isLikedByCurrentUser = likedByCurrentUser;
    }

    public void addLikes(Likes likes) {
        this.likes.add(likes);
    }

    public void deleteLikes(Likes likes) {
        this.likes.remove(likes);
    }

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

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    @PrePersist
    private void onPrePersist() {
        if (date == null) {
            date = new Date();
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "uuid="+ uuid+
                ", parent=" + parent +
                ", item=" + item +
                ", likeCnt=" + likeCnt +
                ", reportCnt=" + reportCnt +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
