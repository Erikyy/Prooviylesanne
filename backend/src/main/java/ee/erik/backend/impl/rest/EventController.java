package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.ErrorDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.domain.services.EventSelector;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * This controller manages both events and participants.
 */
@RestController()
@RequestMapping("/api/v1/events")
@Tag(name = "Events/Üritused", description = "Events API / Ürituste API ")
public class EventController {

    private final EventManager manager;

    @Autowired
    public EventController(EventManager manager) {
        this.manager = manager;
    }

    @Operation(summary = "List all events by before today or after today / Kuvab üritused kas enne või peale tänast kuupäeva", description = "List all events by before today or after today / Kuvab üritused kas enne või peale tänast kuupäeva", tags = {"Events"})
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EventDto.class))))
    @GetMapping(produces = { "application/json" })
    public Set<EventDto> events(@RequestParam(value = "event", required = false) EventSelector eventSelector) {
        return this.manager.findEvents(eventSelector);
    }

    @Operation(summary = "Get event by id / Tagastab ürituse id kaudu", description = "Returns a event by id. / Tagastab ürituse id kaudu.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping(path = "/{id}", produces = { "application/json" })
    public EventDto getEventById(@PathVariable Long id) {
        return this.manager.getEventById(id);
    }

    @Operation(summary = "Creates new event / Loob uue ürituse", description = "Event can only be created using future date / Üritust on võimalik luua ainult tuleviku kuupäevaga", tags = {"Events"})
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EventDto.class))))
    @PostMapping(produces = { "application/json" })
    public EventDto createNewEvent(@RequestBody CreateEventDto createEventDto) {
        return this.manager.createNewEvent(createEventDto);
    }

    @Operation(summary = "Deletes an event / Kustutab ürituse", description = "Deletes an event. Only possible for events that haven't taken place / Kustutab ürituse. Ainult võimalik üritustel mis pole veel toimunud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden deletion. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @DeleteMapping(path = "/{id}", produces = { "application/json" })
    public void deleteEvent(@PathVariable Long id) {
        this.manager.deleteEvent(id);
    }

    @Operation(summary = "Return all participants regitered to an event by id / Tagastab kõik osalejad regiteeritud üritusele läbi ürituse id", description = "Return all participants regitered to an event by id / Tagastab kõik osalejad regiteeritud üritusele läbi ürituse id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ParticipantDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping(path = "/{id}/participants", produces = { "application/json" })
    public Set<ParticipantDto> findAllParticipantsInEvent(@PathVariable Long id) {
        return this.manager.findAllParticipantsInEvent(id);
    }

    @Operation(summary = "Returns single participant in event by id / Tagastab ühe osaleja ürituse id kaudu", description = "Returns single participant in event by id / Tagastab ühe osaleja ürituse id kaudu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ParticipantDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public ParticipantDto findParticipantInEventById(@PathVariable Long eventId, @PathVariable Long participantId) {
        return this.manager.findParticipantInEventById(eventId, participantId);
    }

    @Operation(summary = "Adds a participant to an event / Lisab osaleja üritusse", description = "Adds a participant to an event / Lisab osaleja üritusse. Participants can be added only to events that haven't taken place / Osalejaid saab ainult lisada üritustesse mis pole toimunud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden deletion. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping(path = "/{eventId}/participants", produces = { "application/json" })
    public ParticipantDto addParticipantToEvent(@PathVariable Long eventId, @RequestBody CreateParticipantDto createParticipantDto) {
        System.out.println(createParticipantDto.toString());
        return this.manager.addParticipantToEvent(eventId, createParticipantDto);
    }

    @Operation(summary = "Updates a participant in an event / Uuendab osaleja infot ürituses", description = "Updates a participant in an event / Uuendab osaleja infot ürituses. Participants can be updated only in events that haven't taken place / Osalejaid saab ainult uuendada üritustes mis pole toimunud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden deletion. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PutMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public ParticipantDto updateParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody UpdateParticipantDto updateParticipantDto) {
        return this.manager.updateParticipantInEvent(eventId, participantId, updateParticipantDto);
    }

    @Operation(summary = "Deletes a participant in an event / Kustutab osaleja infot ürituses", description = "Deletes a participant in an event / Kustutab osaleja infot ürituses. Participants can be deleted only in events that haven't taken place / Osalejaid saab ainult kustutada üritustes mis pole toimunud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden deletion. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @DeleteMapping(path = "/{eventId}/participants/{participantId}", produces = { "application/json" })
    public void deleteParticipantInEvent(@PathVariable Long eventId, @PathVariable Long participantId) {
        this.manager.deleteParticipantFromEvent(eventId, participantId);
    }
}
