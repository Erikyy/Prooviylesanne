package ee.erik.core.application.managers;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.services.EventService;
import ee.erik.core.domain.services.exceptions.EventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventsManager {


    private final EventService eventService;

    @Autowired
    public EventsManager(EventService eventService) {
        this.eventService = eventService;
    }

    public Event addEvent(Event event) throws EventException {
        return this.eventService.createNewEvent(event);
    }

    public Event deleteEvent(Long id) throws EventException {
        return this.eventService.deleteEvent(id);
    }

    public Participant addParticipant(Participant participant, Long eventId) throws EventException {
        return this.eventService.addParticipantToEvent(participant, eventId);
    }

    public Participant updateParticipant(Participant participant, Long eventId) throws EventException {
        return this.eventService.updateParticipantInEvent(participant, eventId);
    }

    public void removeParticipant(Long eventId, Long participantId) throws EventException {
        this.eventService.removeParticipantFromEvent(eventId, participantId);
    }

}
