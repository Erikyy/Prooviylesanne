package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;
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
        if (event.getDate().after(new Date())) {
            return this.eventRepository.save(event);
        } else {
            throw new DomainEventDateException("Date: " + event.getDate().toString() + " is not accepted");
        }

    }

    public void deleteEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            //check date here maybe throw some exception
            if (event.get().getDate().after(new Date())) {
                this.eventRepository.delete(event.get());
            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        }

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
            if (event.get().getDate().after(new Date())) {
                event.get().getParticipants().add(participant);
                this.eventRepository.save(event.get());
                return Optional.of(participant);
            } else  {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<Participant> updateParticipantInEvent(Long eventId, Participant participant) {
        Optional<Event> event = this.eventRepository.findById(eventId);

        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                Optional<Participant> foundParticipant = this.participantRepository.findById(participant.getId());
                if (foundParticipant.isPresent()) {
                    return Optional.of(this.participantRepository.save(participant));
                } else {
                    return Optional.empty();
                }
            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            return Optional.empty();
        }
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                Optional<Participant> participant = this.participantRepository.findById(participantId);
                participant.ifPresent(this.participantRepository::delete);
            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            //throw exception
        }


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
