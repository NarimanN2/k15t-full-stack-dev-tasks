package com.k15t.pat.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class ExceptionDto {

    private HttpStatus status;

    private Date timestamp;

    private List<String> messages;

    public ExceptionDto(List<String> messages, HttpStatus status) {
        this.messages = messages;
        this.status = status;
        this.timestamp = new Date();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ExceptionDto{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", message='" + messages + '\'' +
                '}';
    }
}
