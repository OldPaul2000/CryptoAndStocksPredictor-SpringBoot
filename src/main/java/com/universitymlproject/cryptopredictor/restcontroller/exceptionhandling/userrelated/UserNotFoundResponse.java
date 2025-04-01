package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.userrelated;

import java.time.LocalDateTime;

public class UserNotFoundResponse {

    private int httpStatus;
    private String message;
    private LocalDateTime timestamp;

    public UserNotFoundResponse() {}

    public UserNotFoundResponse(int httpStatus, String message, LocalDateTime timestamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
