package com.aldisued.iot.monitoring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Generic exception")
public class EntityNotFoundException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    public EntityNotFoundException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public EntityNotFoundException() {}
}
