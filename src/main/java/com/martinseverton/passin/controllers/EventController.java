package com.martinseverton.passin.controllers;

import com.martinseverton.passin.domain.attendee.Attendee;
import com.martinseverton.passin.dto.attendee.AttendeeDetailsDTO;
import com.martinseverton.passin.dto.attendee.AttendeesListResponseDTO;
import com.martinseverton.passin.dto.event.EventIdDTO;
import com.martinseverton.passin.dto.event.EventRequestDTO;
import com.martinseverton.passin.dto.event.EventResponseDTO;
import com.martinseverton.passin.services.AttendeeService;
import com.martinseverton.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable String id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
