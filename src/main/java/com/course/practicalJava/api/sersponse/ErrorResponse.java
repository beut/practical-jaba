package com.course.practicalJava.api.sersponse;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

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

    public ErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorResponse() {
    }


}
