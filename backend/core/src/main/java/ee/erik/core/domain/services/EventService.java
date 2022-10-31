package ee.erik.core.domain.services;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.domain.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ParticipantRepository participantRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    public Event createNewEvent(Event event) {
        return this.eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        event.ifPresent(value -> this.eventRepository.delete(event.get()));
    }

    public Set<Event> findEventsBeforeToday() {
        return this.eventRepository.findBeforeDate(new Date());
    }

    public Set<Event> findEventsAfterToday() {
        return this.eventRepository.findAfterDate(new Date());
    }


    public Optional<Participant> addParticipantToEvent(Long eventId, Participant participant) {

        Optional<Event> event = this.eventRepository.findById(eventId);

        if(event.isPresent()) {
            event.get().getParticipants().add(participant);
            this.eventRepository.save(event.get());
            return Optional.of(participant);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Participant> updateParticipantInEvent(Long eventId, Participant participant) {
        Optional<Event> event = this.eventRepository.findById(eventId);

        if (event.isPresent()) {
            Optional<Participant> foundParticipant = this.participantRepository.findById(participant.getId());
            if (foundParticipant.isPresent()) {
                return Optional.of(this.participantRepository.save(participant));
            } else {
                return Optional.empty();
            }

        } else {
            return Optional.empty();
        }
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        Optional<Participant> participant = this.participantRepository.findById(participantId);
        participant.ifPresent(value -> this.participantRepository.delete(value));
    }

    public Set<Participant> findAllParticipantsInEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(event.isPresent()) {
            return event.get().getParticipants();
        } else {
            return new HashSet<>();
        }
    }

    public Optional<Event> getEventById(Long id) {
        return this.eventRepository.findById(id);
    }

    public Optional<Participant> findParticipantInEventById(Long eventId, Long participantId) {
        return this.participantRepository.findById(participantId);
    }

}
