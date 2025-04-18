package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.files;

import java.time.LocalDateTime;

public class FilesErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    public FilesErrorResponse() {}

    public FilesErrorResponse(LocalDateTime timestamp, int status, String message) {
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
