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

    Event updateEvent(Event event);

    void deleteEvent(Long eventId);
    Set<Event> findEvents(EventSelector eventSelector);

    /**
     * Adds either new or existing participant to an event.
     * If participant id is null then it creates new participant otherwise it will use existing
     * participant reference
     * @param eventId
     * @param participant
     * @return
     */
    Participant addParticipantToEvent(Long eventId, Participant participant);

    /**
     * This only updates participant, doesn't care what participant references are stored in event
     * @param participant
     * @return
     */
    Participant updateParticipant(Participant participant);

    /**
     * Deletes participant reference from event, participant will still be stored in database
     * @param eventId
     * @return
     */
    void deleteParticipantFromEvent(Long eventId, Long participantId);

    Set<Participant> findAllParticipantsInEvent(Long eventId);
    Event getEventById(Long id);
    Participant findParticipantInEventById(Long eventId, Long participantId);

    Set<Participant> findAllParticipants();
}
