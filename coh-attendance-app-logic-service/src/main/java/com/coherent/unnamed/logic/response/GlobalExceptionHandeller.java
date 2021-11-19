package com.coherent.unnamed.logic.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeller {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handelerException(IllegalArgumentException e){
        return new ResponseEntity<Object>("Illgal Arg exception", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handelerException(BadRequestException e){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(e.getErrors());
        return ResponseEntity.status(400).body(apiResponse);
    }
}
