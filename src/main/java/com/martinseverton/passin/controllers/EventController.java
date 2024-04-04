package com.martinseverton.passin.controllers;

import com.martinseverton.passin.dto.attendee.AttendeeIdDTO;
import com.martinseverton.passin.dto.attendee.AttendeeRequestDTO;
import com.martinseverton.passin.dto.attendee.AttendeesListResponseDTO;
import com.martinseverton.passin.dto.event.EventIdDTO;
import com.martinseverton.passin.dto.event.EventRequestDTO;
import com.martinseverton.passin.dto.event.EventResponseDTO;
import com.martinseverton.passin.services.AttendeeService;
import com.martinseverton.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO eventResponseDTO = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(eventResponseDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO attendeesListResponseDTO = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponseDTO);
    }


    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipante(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable String id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
