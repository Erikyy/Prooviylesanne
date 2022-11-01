package ee.erik.core.application.managers;

import ee.erik.core.application.managers.dto.CreateEventDto;
import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class EventManager {

    private EventService eventService;

    @Autowired
    public EventManager(EventService eventService) {
        this.eventService = eventService;
    }

    public Event createNewEvent(CreateEventDto createEventDto) {
        Event event = new Event();
        event.setName(createEventDto.getName());
        event.setDate(createEventDto.getDate());
        event.setLocation(createEventDto.getLocation());
        event.setInfo(createEventDto.getInfo());
        event.setParticipants(new HashSet<>());
        return this.eventService.createNewEvent(event);
    }

    public void deleteEvent(Long eventId) {
        this.eventService.deleteEvent(eventId);
    }

    public Set<Event> findEventsBeforeToday() {
        return this.eventService.findEventsBeforeToday();
    }

    public Set<Event> findEventsAfterToday() {
        return this.eventService.findEventsAfterToday();
    }

    public Optional<Participant> addParticipantToEvent(Long eventId, Participant participant) {
        return this.eventService.addParticipantToEvent(eventId, participant);
    }

    public Optional<Participant> updateParticipantInEvent(Long eventId, Participant participant) {
        return this.eventService.updateParticipantInEvent(eventId, participant);
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        this.eventService.deleteParticipantFromEvent(eventId, participantId);
    }

    public Set<Participant> findAllParticipantsInEvent(Long eventId) {
        return this.eventService.findAllParticipantsInEvent(eventId);
    }

    public Optional<Event> getEventById(Long id) {
        return this.eventService.getEventById(id);
    }

    public Optional<Participant> findParticipantInEventById(Long eventId, Long participantId) {
        return this.eventService.findParticipantInEventById(eventId, participantId);
    }
}
