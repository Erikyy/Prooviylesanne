package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;
import ee.erik.backend.domain.services.exceptions.DomainNotFoundException;
import ee.erik.backend.domain.services.exceptions.DomainUnableToAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * This service class basically manages events and participant, does also date checking and throws exceptions.
 */
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ParticipantRepository participantRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setParticipantRepository(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Event createNewEvent(Event event) {
        if (event.getDate().after(new Date())) {
            return this.eventRepository.save(event);
        } else {
            throw new DomainEventDateException("Date: " + event.getDate().toString() + " is not accepted");
        }

    }

    @Override
    public Event updateEvent(Event event) {
        if (event.getDate().after(new Date())) {
            return this.eventRepository.save(event);
        } else {
            throw new DomainEventDateException("Date: " + event.getDate().toString() + " is not accepted");
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            //check date here maybe throw some exception
            if (event.get().getDate().after(new Date())) {
                this.eventRepository.delete(event.get());
            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }

    }

    @Override
    public Set<Event> findEvents(EventSelector eventSelector) {
        if (eventSelector == null) {
            return this.eventRepository.findAll();
        } else {
            switch (eventSelector) {
                case After -> {
                    return this.eventRepository.findAllAfterDate(new Date());
                }
                case Before -> {
                    return this.eventRepository.findAllBeforeDate(new Date());
                }
                default -> { return this.eventRepository.findAll(); }
            }
        }
    }

    @Override
    public Participant addParticipantToEvent(Long eventId, Participant participant) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                if (participant.getBusiness() == null && participant.getCitizen() == null) {
                    throw new DomainUnableToAddException("Cannot add participant that have both citizen and business undefined");
                }
                if (participant.getId() == null) {
                    Participant savedParticipant = this.participantRepository.save(participant);
                    if (this.eventRepository.saveWithParticipant(event.get(), savedParticipant.getId()).isEmpty()) {
                        throw new DomainUnableToAddException("Error adding participant to event");
                    }
                } else {
                    if (this.eventRepository.saveWithParticipant(event.get(), participant.getId()).isEmpty()) {
                        throw new DomainUnableToAddException("Error adding participant to event");
                    }
                }
                return participant;
            } else  {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    @Override
    public Participant updateParticipant(Participant participant) {
        Optional<Participant> foundParticipant = this.participantRepository.findById(participant.getId());
        System.out.println(participant);
        if (foundParticipant.isPresent()) {
            return this.participantRepository.save(participant);
        } else {
            throw new DomainNotFoundException("Participant not found: " + participant.getId() + " " + participant.getName());
        }
    }

    @Override
    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        //check that date is in future and that the participant exists
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                Optional<Participant> participant = this.participantRepository.findByIdInEventById(participantId, eventId);
                if (participant.isPresent()) {
                    this.participantRepository.delete(participantId, eventId);
                } else {
                    throw new DomainNotFoundException("Participant not found: " + participantId);
                }

            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    @Override
    public Set<Participant> findAllParticipantsInEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(event.isPresent()) {
            return this.participantRepository.findAllByEvent(eventId);
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    @Override
    public Event getEventById(Long id) {
        Optional<Event> event = this.eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new DomainNotFoundException("Event not found: " + id);
        }
    }

    @Override
    public Participant findParticipantInEventById(Long eventId, Long participantId) {
        Optional<Participant> participant = this.participantRepository.findByIdInEventById(participantId, eventId);
        if (participant.isPresent()) {
            return participant.get();
        } else {
            throw new DomainNotFoundException("Participant: " + participantId +  " not found in event: " + eventId);
        }
    }

    @Override
    public Set<Participant> findAllParticipants() {
        return this.participantRepository.findAll();
    }
}
