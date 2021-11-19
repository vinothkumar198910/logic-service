package com.coherent.unnamed.logic.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends RuntimeException{
    private HttpStatus errors;

    public HttpStatus getErrors(){
        return errors;
    }

    public void setErrors(HttpStatus errors) {
        this.errors = errors;
    }

    public BadRequestException(String message, HttpStatus errors) {
        super(message);
        this.errors = errors;
    }
}
