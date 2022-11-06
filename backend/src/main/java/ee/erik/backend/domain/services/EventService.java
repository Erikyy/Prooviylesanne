package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;
import ee.erik.backend.domain.services.exceptions.DomainNotFoundException;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface EventService {
    Event createNewEvent(Event event);

    void deleteEvent(Long eventId);
    Set<Event> findEvents(EventSelector eventSelector);
    Participant addParticipantToEvent(Long eventId, Participant participant);
    Participant updateParticipantInEvent(Long eventId, Participant participant);
    void deleteParticipantFromEvent(Long eventId, Long participantId);
    Set<Participant> findAllParticipantsInEvent(Long eventId);
    Event getEventById(Long id);
    Participant findParticipantInEventById(Long eventId, Long participantId);
}
