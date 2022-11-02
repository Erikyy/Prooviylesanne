package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.CreateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.dto.CreateEventDto;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
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
    public Set<Event> events(@RequestParam(value = "event", required = false) String date) {
        //TODO: Move this to service class, controller should not have this logic
        if (date != null) {
            switch (date) {
                case "before" -> {
                    return this.manager.findEventsAfterToday();
                }
                case "after" -> {
                    return this.manager.findEventsBeforeToday();
                }
                default -> {
                    return new HashSet<>();
                }
            }
        } else {
            return new HashSet<>();
        }

    }

    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable Long id) {
        return this.manager.getEventById(id);
    }

    @PostMapping("/events")
    public Event createNewEvent(@RequestBody CreateEventDto createEventDto) {
        return this.manager.createNewEvent(createEventDto);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        this.manager.deleteEvent(id);
    }

    @GetMapping("/events/{id}/participants")
    public Set<Participant> findAllParticipantsInEvent(@PathVariable Long id) {
        return this.manager.findAllParticipantsInEvent(id);
    }

    @GetMapping("/events/{eventId}/participants/{participantId}")
    public Participant findParticipantInEventById(@PathVariable Long eventId, @PathVariable Long participantId) {
        return this.manager.findParticipantInEventById(eventId, participantId);
    }

    @PostMapping("/events/{eventId}/participants")
    public Participant addParticipantToEvent(@PathVariable Long eventId, @RequestBody CreateParticipantDto createParticipantDto) {
        System.out.println(createParticipantDto.toString());
        return this.manager.addParticipantToEvent(eventId, createParticipantDto);
    }

    @PutMapping("/events/{eventId}/participants/{participantId}")
    public Participant updateParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody Participant participant) {
        return this.manager.updateParticipantInEvent(eventId, participant);
    }

    @DeleteMapping("/events/{eventId}/participants/{participantId}")
    public void deleteParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId) {
        this.manager.deleteParticipantFromEvent(eventId, participantId);
    }
}
