package com.martinseverton.passin.services;

import com.martinseverton.passin.domain.attendee.Attendee;
import com.martinseverton.passin.domain.event.Event;
import com.martinseverton.passin.domain.event.exceptions.EventNotFoundException;
import com.martinseverton.passin.dto.event.EventIdDTO;
import com.martinseverton.passin.dto.event.EventRequestDTO;
import com.martinseverton.passin.dto.event.EventResponseDTO;
import com.martinseverton.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public List<EventResponseDTO> getEvents(){
//        int attendeesAmountByEvent;
//        List<Event> eventList = this.eventRepository.findAll();
//        if(eventList.isEmpty()){
//            throw new EventNotFoundException("Events not found!");
//        }else {
//            attendeesAmountByEvent = eventList.stream().map(event -> {
//
//            });
//        }
//
//            List<EventResponseDTO> eventResponseDTO = eventList.stream().map(e -> new EventResponseDTO(e,attendeesAmountByEvent)).collect(Collectors.toList());
//            return eventResponseDTO;
        return null;
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));
        eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());
    }

    public void deleteEvent(String id){
        eventRepository.deleteById(id);
    }

    private String createSlug(String text){
        String normalize = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalize.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+","-")
                .toLowerCase();
    }
}
