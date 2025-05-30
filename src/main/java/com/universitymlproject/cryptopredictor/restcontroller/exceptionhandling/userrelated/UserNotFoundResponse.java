package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.userrelated;

import java.time.LocalDateTime;

public class UserNotFoundResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    public UserNotFoundResponse() {}
    public UserNotFoundResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
