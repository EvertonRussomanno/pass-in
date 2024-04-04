package com.martinseverton.passin.config;

import com.martinseverton.passin.domain.event.exceptions.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handlerEventNotFound(EventNotFoundException exception){
        String body = exception.getMessage();
        return ResponseEntity.status(404).body(body);
    }
}
