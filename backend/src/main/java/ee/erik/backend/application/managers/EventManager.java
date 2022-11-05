package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.*;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.Business;
import ee.erik.backend.domain.entities.participant.Citizen;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is used for interfacing outside the application layer, currently only used in impl/presentation folder.
 */
@Component
public class EventManager {

    private final EventService eventService;

    @Autowired
    public EventManager(EventService eventService) {
        this.eventService = eventService;
    }

    public EventDto createNewEvent(CreateEventDto createEventDto) {
        return Converters.convertToEventDto(this.eventService.createNewEvent(Converters.convertEventCreateDtoToEvent(createEventDto)));
    }

    public void deleteEvent(Long eventId) {
        this.eventService.deleteEvent(eventId);
    }

    public Set<EventDto> findEvents(String beforeAfterDateString) {
        return this.eventService.findEvents(beforeAfterDateString).stream().map(Converters::convertToEventDto).collect(Collectors.toSet());
    }

    public ParticipantDto addParticipantToEvent(Long eventId, CreateParticipantDto createParticipantDto) {
        return Converters.convertToParticipantDto(this.eventService.addParticipantToEvent(eventId, Converters.convertCreateDtoToParticipant(createParticipantDto)));
    }

    public ParticipantDto updateParticipantInEvent(Long eventId, Long participantId, UpdateParticipantDto updateParticipantDto) {
        return Converters.convertToParticipantDto(this.eventService.updateParticipantInEvent(eventId, Converters.convertUpdateParticipantDtoToParticipant(updateParticipantDto)));
    }

    public void deleteParticipantFromEvent(Long eventId, Long participantId) {
        this.eventService.deleteParticipantFromEvent(eventId, participantId);
    }

    public Set<ParticipantDto> findAllParticipantsInEvent(Long eventId) {
        return this.eventService.findAllParticipantsInEvent(eventId).stream().map(Converters::convertToParticipantDto).collect(Collectors.toSet());
    }

    public EventDto getEventById(Long id) {
        return Converters.convertToEventDto(this.eventService.getEventById(id));
    }

    public ParticipantDto findParticipantInEventById(Long eventId, Long participantId) {
        return Converters.convertToParticipantDto(this.eventService.findParticipantInEventById(eventId, participantId));
    }
}
