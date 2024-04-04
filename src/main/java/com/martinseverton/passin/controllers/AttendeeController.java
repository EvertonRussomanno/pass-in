package com.martinseverton.passin.controllers;

import com.martinseverton.passin.domain.attendee.Attendee;
import com.martinseverton.passin.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Attendee>> getAllAttendeesFromEvent(String id){
        return ResponseEntity.ok(attendeeService.getAllAttendeesFromEvent(id));
    }
}
