package com.couchtalks.entity.web.ajax;

import java.util.UUID;

/**
 * Created by NShkarupa on 09.08.2017.
 */
public class LikeSearchCriteria {
    private UUID commentUUID;
    private Long userId;

    public UUID getCommentUUID() {
        return commentUUID;
    }

    public void setCommentUUID(UUID commentUUID) {
        this.commentUUID = commentUUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LikeSearchCriteria{" +
                "commentUUID=" + commentUUID +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikeSearchCriteria that = (LikeSearchCriteria) o;

        if (commentUUID != null ? !commentUUID.equals(that.commentUUID) : that.commentUUID != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    @Override
    public int hashCode() {
        int result = commentUUID != null ? commentUUID.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
