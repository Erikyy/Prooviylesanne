package ee.erik.core.domain.services;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.domain.repositories.participant.BusinessRepository;
import ee.erik.core.domain.repositories.participant.CitizenRepository;
import ee.erik.core.domain.services.exceptions.EventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ParticipantRepository participantRepository;
    private CitizenRepository citizenRepository;
    private BusinessRepository businessRepository;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            ParticipantRepository participantRepository,
            CitizenRepository citizenRepository,
            BusinessRepository businessRepository
    ) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.citizenRepository = citizenRepository;
        this.businessRepository = businessRepository;
    }

    public Event createNewEvent(Event event) throws EventException {
        //check that datetime has not passed, in that case throw exception
        if (event.getDate().before(new Date())) {
            throw new EventException(EventException.EventExceptionStatus.DateHasAlreadyPassed, 0L);
        } else {
            return this.eventRepository.save(event);
        }
    }

    public Participant addParticipantToEvent(Participant participant, Long eventId) throws EventException {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().before(new Date())) {
                throw new EventException(EventException.EventExceptionStatus.DateHasAlreadyPassed, eventId);
            } else {
                if (participant.getCitizen() != null && participant.getBusiness() == null) {
                    this.citizenRepository.save(participant.getCitizen());
                } else if (participant.getBusiness() != null && participant.getCitizen() == null) {
                    this.businessRepository.save(participant.getBusiness());
                } else {
                    throw new EventException(EventException.EventExceptionStatus.FailedToCreate, eventId);
                }

                this.participantRepository.save(participant);
                return participant;
            }
        } else {
            throw new EventException(EventException.EventExceptionStatus.EventNotFound, eventId);
        }
    }

    public Participant updateParticipantInEvent(Participant participant, Long eventId) throws EventException {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().before(new Date())) {
                throw new EventException(EventException.EventExceptionStatus.DateHasAlreadyPassed, eventId);
            } else {
                Optional<Participant> foundParticipant = this.participantRepository.findById(participant.getId());
                if (foundParticipant.isPresent()) {
                    //make sure that participant hasn't swapped between citizen and business
                    if (participant.getCitizen() != null && foundParticipant.get().getCitizen() != null && Objects.equals(participant.getCitizen().getId(), foundParticipant.get().getCitizen().getId()) && participant.getBusiness() == null && foundParticipant.get().getBusiness() == null) {
                        this.citizenRepository.update(participant.getCitizen());
                    } else if (participant.getBusiness() != null && foundParticipant.get().getBusiness() != null && Objects.equals(participant.getBusiness().getId(), foundParticipant.get().getBusiness().getId()) && participant.getCitizen() == null && foundParticipant.get().getCitizen() == null) {
                        this.businessRepository.update(participant.getBusiness());
                    } else {
                        throw new EventException(EventException.EventExceptionStatus.InvalidUpdate, participant.getId());
                    }
                    return this.participantRepository.update(participant);
                } else {
                    throw new EventException(EventException.EventExceptionStatus.ParticipantNotFound, participant.getId());
                }
            }
        } else {
            throw new EventException(EventException.EventExceptionStatus.EventNotFound, eventId);
        }
    }

    public void removeParticipantFromEvent(Long eventId, Long participantId) throws EventException {
        Optional<Event> event = this.eventRepository.findById(eventId);
        if (event.isPresent()) {
            if (event.get().getDate().before(new Date())) {
                throw new EventException(EventException.EventExceptionStatus.FailedToDelete, eventId);
            } else {
                Optional<Participant> participant = this.participantRepository.findById(participantId);
                if (participant.isPresent()) {
                    this.participantRepository.deleteById(participantId);
                } else {
                    throw new EventException(EventException.EventExceptionStatus.ParticipantNotFound, participantId);
                }
            }
        } else {
            throw new EventException(EventException.EventExceptionStatus.EventNotFound, eventId);
        }
    }

    public Event deleteEvent(Long id) throws EventException {
        Optional<Event> event = this.eventRepository.findById(id);

        if (event.isPresent()) {
            if (event.get().getDate().before(new Date())) {
                throw new EventException(EventException.EventExceptionStatus.DateHasAlreadyPassed, id);
            } else {
                this.eventRepository.deleteById(id);
                return event.get();
            }
        } else {
            throw new EventException(EventException.EventExceptionStatus.EventNotFound, id);
        }
    }
}
