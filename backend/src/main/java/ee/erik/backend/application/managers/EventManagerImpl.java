package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.*;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.services.EventSelector;
import ee.erik.backend.domain.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is used for interfacing outside the application layer, currently only used in impl/presentation folder.
 * Also, the main goal with this class is to convert domain entities to dto's and back.
 */
@Component
public class EventManagerImpl implements EventManager {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public EventDto createNewEvent(CreateEventDto createEventDto) {
        return Converters.convertToEventDto(this.eventService.createNewEvent(Converters.convertEventCreateDtoToEvent(createEventDto)));
    }

    public void deleteEvent(Long eventId) {
        this.eventService.deleteEvent(eventId);
    }

    public Set<EventDto> findEvents(EventSelector eventSelector) {
        return this.eventService.findEvents(eventSelector).stream().map(Converters::convertToEventDto).collect(Collectors.toSet());
    }

    public ParticipantDto addParticipantToEvent(Long eventId, CreateParticipantDto createParticipantDto) {
        return Converters.convertToParticipantDto(this.eventService.addParticipantToEvent(eventId, Converters.convertCreateDtoToParticipant(createParticipantDto)));
    }

    public ParticipantDto updateParticipantInEvent(Long eventId, Long participantId, UpdateParticipantDto updateParticipantDto) {
        Participant participant = Converters.convertUpdateParticipantDtoToParticipant(updateParticipantDto);
        participant.setId(participantId);
        return Converters.convertToParticipantDto(this.eventService.updateParticipantInEvent(eventId, participant));
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
