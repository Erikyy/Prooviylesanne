package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.CreateEventDto;
import ee.erik.backend.application.dto.CreateParticipantDto;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.Business;
import ee.erik.backend.domain.entities.participant.Citizen;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Participant addParticipantToEvent(Long eventId, CreateParticipantDto createParticipantDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(createParticipantDto.getPaymentMethodId());
        Participant participant = new Participant();
        participant.setName(createParticipantDto.getName());
        participant.setPaymentMethod(paymentMethod);

        if (createParticipantDto.getCitizen() != null) {
            Citizen citizen = new Citizen();
            citizen.setInfo(createParticipantDto.getCitizen().getInfo());
            citizen.setLastName(createParticipantDto.getCitizen().getLastName());
            citizen.setIdNumber(createParticipantDto.getCitizen().getIdNumber());
            participant.setCitizen(citizen);
        } else if (createParticipantDto.getBusiness() != null) {
            Business business = new Business();
            business.setNumOfParticipants(createParticipantDto.getBusiness().getNumOfParticipants());
            business.setRegCode(createParticipantDto.getBusiness().getRegCode());
            business.setInfo(createParticipantDto.getBusiness().getInfo());
            participant.setBusiness(business);
        }
        return this.eventService.addParticipantToEvent(eventId, participant);
    }

    public Participant updateParticipantInEvent(Long eventId, Participant participant) {
        return this.eventService.updateParticipantInEvent(eventId, participant);
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        this.eventService.deleteParticipantFromEvent(eventId, participantId);
    }

    public Set<Participant> findAllParticipantsInEvent(Long eventId) {
        return this.eventService.findAllParticipantsInEvent(eventId);
    }

    public Event getEventById(Long id) {
        return this.eventService.getEventById(id);
    }

    public Participant findParticipantInEventById(Long eventId, Long participantId) {
        return this.eventService.findParticipantInEventById(eventId, participantId);
    }
}
