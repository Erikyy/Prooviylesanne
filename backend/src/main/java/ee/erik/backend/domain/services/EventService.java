package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;
import ee.erik.backend.domain.services.exceptions.DomainNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

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
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }

    }

    public Set<Event> findEvents(String dateBeforeAfter) {
        if (dateBeforeAfter == null) {
            return this.eventRepository.findAll();
        } else {
            if (dateBeforeAfter.equals("before")) {
                return this.eventRepository.findAllBeforeDate(new Date());
            } else if (dateBeforeAfter.equals("after")) {
                return this.eventRepository.findAllAfterDate(new Date());
            } else {
                return this.eventRepository.findAll();
            }
        }
    }



    public Participant addParticipantToEvent(Long eventId, Participant participant) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                event.get().getParticipants().add(participant);
                this.eventRepository.save(event.get());
                return participant;
            } else  {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    public Participant updateParticipantInEvent(Long eventId, Participant participant) {
        Optional<Event> event = this.eventRepository.findById(eventId);

        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                Optional<Participant> foundParticipant = this.participantRepository.findById(participant.getId());
                if (foundParticipant.isPresent()) {
                    return this.participantRepository.save(participant);
                } else {
                    throw new DomainNotFoundException("Participant not found: " + participant.getId() + " " + participant.getName());
                }
            } else {
                throw new DomainEventDateException("Date: " + event.get().getDate().toString() + " is not accepted");
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().after(new Date())) {
                Optional<Participant> participant = this.participantRepository.findById(participantId);
                if (participant.isPresent()) {
                    this.participantRepository.delete(participant.get());
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

    public Set<Participant> findAllParticipantsInEvent(Long eventId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if(event.isPresent()) {
            return event.get().getParticipants();
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }
    }

    public Event getEventById(Long id) {
        Optional<Event> event = this.eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new DomainNotFoundException("Event not found: " + id);
        }
    }

    public Participant findParticipantInEventById(Long eventId, Long participantId) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        //verify that event actually exists
        if (event.isPresent()) {
            Optional<Participant> participant = this.participantRepository.findById(participantId);
            if (participant.isPresent()) {
                if (event.get().getParticipants().contains(participant.get())) {
                    return participant.get();
                } else {
                    throw new DomainNotFoundException("Something isn't right, participant: "+ participantId +" not found in event: " + eventId);
                }

            } else {
                throw new DomainNotFoundException("Participant not found: " + eventId);
            }
        } else {
            throw new DomainNotFoundException("Event not found: " + eventId);
        }

    }

}
