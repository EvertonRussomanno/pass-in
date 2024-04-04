package com.martinseverton.passin.config;

import com.martinseverton.passin.domain.attendee.exceptions.AttendeeAlreadyExistsException;
import com.martinseverton.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.martinseverton.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import com.martinseverton.passin.domain.event.exceptions.EventFullException;
import com.martinseverton.passin.domain.event.exceptions.EventNotFoundException;
import com.martinseverton.passin.dto.general.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handlerEventNotFound(EventNotFoundException exception){
        String body = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDto> handlerEventFull(EventFullException exception){
        String body = exception.getMessage();
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<String> handlerAttendeeNotFound(AttendeeNotFoundException exception){
        String body = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(AttendeeAlreadyExistsException.class)
    public ResponseEntity<String> handlerAttendeeAlreadyExists(AttendeeAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity<String> handlerCheckInAlreadyExists(CheckInAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
