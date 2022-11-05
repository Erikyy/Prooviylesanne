package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/api/v1/events")
@Tag(name = "Events/Üritused", description = "Events API / Ürituste API ")
public class EventController {

    private final EventManager manager;

    @Autowired
    public EventController(EventManager manager) {
        this.manager = manager;
    }

    @GetMapping(produces = { "application/json" })
    public Set<EventDto> events(@RequestParam(value = "event", required = false) String date) {
        return this.manager.findEvents(date);
    }

    @GetMapping(path = "/{id}", produces = { "application/json" })
    public EventDto getEventById(@PathVariable Long id) {
        return this.manager.getEventById(id);
    }

    @PostMapping(produces = { "application/json" })
    public EventDto createNewEvent(@RequestBody CreateEventDto createEventDto) {
        return this.manager.createNewEvent(createEventDto);
    }

    @DeleteMapping(path = "/{id}", produces = { "application/json" })
    public void deleteEvent(@PathVariable Long id) {
        this.manager.deleteEvent(id);
    }

    @GetMapping(path = "/{id}/participants", produces = { "application/json" })
    public Set<ParticipantDto> findAllParticipantsInEvent(@PathVariable Long id) {
        return this.manager.findAllParticipantsInEvent(id);
    }

    @GetMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public ParticipantDto findParticipantInEventById(@PathVariable Long eventId, @PathVariable Long participantId) {
        return this.manager.findParticipantInEventById(eventId, participantId);
    }

    @PostMapping(path = "/{eventId}/participants", produces = { "application/json" })
    public ParticipantDto addParticipantToEvent(@PathVariable Long eventId, @RequestBody CreateParticipantDto createParticipantDto) {
        System.out.println(createParticipantDto.toString());
        return this.manager.addParticipantToEvent(eventId, createParticipantDto);
    }

    @PutMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public ParticipantDto updateParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody UpdateParticipantDto updateParticipantDto) {
        return this.manager.updateParticipantInEvent(eventId, participantId, updateParticipantDto);
    }

    @DeleteMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public void deleteParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId) {
        this.manager.deleteParticipantFromEvent(eventId, participantId);
    }
}
