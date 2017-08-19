package com.couchtalks.entity.web.ajax;

import com.couchtalks.entity.Item;
import com.couchtalks.entity.web.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by NShkarupa on 19.08.2017.
 */
public class CreateCommentRequest {
    @JsonProperty
    @JsonIgnoreProperties({"user", "children", "parent", "createdDate", "lastModifiedDate", "version", "deleted", "item", "likeCnt", "reportCnt", "likes", "date", "likedByCurrentUser"})
    public Comment parent;

    @JsonProperty
    @JsonIgnoreProperties({"url", "name", "season", "number", "airdate", "airtime", "airstamp", "runtime", "image", "summary", "show", "_links", "is_featured", "categories"})
    private Item item;

    @JsonProperty
    private String text;

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CreateCommentRequest{" +
                "parent=" + parent +
                ", item=" + item +
                ", text='" + text + '\'' +
                '}';
    }
}
