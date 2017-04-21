package com.couchtalks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by ShkarupaN on 28.03.2017.
 */

@JsonPropertyOrder({
        "error_code",
        "message",
        "detail"
})
public class ErrorResponse {

    public ErrorResponse(String errorCode, String message, String detail) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public ErrorResponse() {
    }

    @JsonProperty("error_code")
    String errorCode;

    @JsonProperty("message")
    String message;

    @JsonProperty("detail")
    String detail;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
