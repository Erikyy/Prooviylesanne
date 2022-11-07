package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.update.UpdateEventDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.domain.services.EventSelector;

import java.util.Set;

/**
 *
 */
public interface EventManager {
    EventDto createNewEvent(CreateEventDto createEventDto);

    EventDto updateEvent(UpdateEventDto updateEventDto);

    void deleteEvent(Long eventId);

    Set<EventDto> findEvents(EventSelector eventSelector);

    ParticipantDto addParticipantToEvent(Long eventId, CreateParticipantDto createParticipantDto);

    ParticipantDto updateParticipant(Long participantId, UpdateParticipantDto updateParticipantDto);

    void deleteParticipantFromEvent(Long eventId, Long participantId);

    Set<ParticipantDto> findAllParticipantsInEvent(Long eventId);

    Set<ParticipantDto> findAllParticipants();

    EventDto getEventById(Long id);

    ParticipantDto findParticipantInEventById(Long eventId, Long participantId);
}
