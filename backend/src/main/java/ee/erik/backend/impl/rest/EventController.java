package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/api/v1")
public class EventController {

    private final EventManager manager;

    @Autowired
    public EventController(EventManager manager) {
        this.manager = manager;
    }

    @GetMapping("/events")
    public Set<EventDto> events(@RequestParam(value = "event", required = false) String date) {
        return this.manager.findEvents(date);
    }

    @GetMapping("/events/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return this.manager.getEventById(id);
    }

    @PostMapping("/events")
    public EventDto createNewEvent(@RequestBody CreateEventDto createEventDto) {
        return this.manager.createNewEvent(createEventDto);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        this.manager.deleteEvent(id);
    }

    @GetMapping("/events/{id}/participants")
    public Set<ParticipantDto> findAllParticipantsInEvent(@PathVariable Long id) {
        return this.manager.findAllParticipantsInEvent(id);
    }

    @GetMapping("/events/{eventId}/participants/{participantId}")
    public ParticipantDto findParticipantInEventById(@PathVariable Long eventId, @PathVariable Long participantId) {
        return this.manager.findParticipantInEventById(eventId, participantId);
    }

    @PostMapping("/events/{eventId}/participants")
    public ParticipantDto addParticipantToEvent(@PathVariable Long eventId, @RequestBody CreateParticipantDto createParticipantDto) {
        System.out.println(createParticipantDto.toString());
        return this.manager.addParticipantToEvent(eventId, createParticipantDto);
    }

    @PutMapping("/events/{eventId}/participants/{participantId}")
    public ParticipantDto updateParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody UpdateParticipantDto updateParticipantDto) {
        return this.manager.updateParticipantInEvent(eventId, participantId, updateParticipantDto);
    }

    @DeleteMapping("/events/{eventId}/participants/{participantId}")
    public void deleteParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId) {
        this.manager.deleteParticipantFromEvent(eventId, participantId);
    }
}
