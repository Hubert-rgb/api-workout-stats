package com.HubertRoszyk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ActivityAssignException extends RuntimeException{
    public ActivityAssignException(String message) {
        super(message);
    }
}
