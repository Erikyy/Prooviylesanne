package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.read.ErrorDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Additional controller to avoid breaking REST pattern
 * Contains routes like find all participants and update
 */
@RestController()
@RequestMapping("/api/v1/participants")
@Tag(name = "Participants/Osalejad", description = "Participants API / Osalejad API ")
public class ParticipantController {

    private final EventManager manager;

    public ParticipantController(EventManager manager) {
        this.manager = manager;
    }

    @Operation(summary = "Updates a participant info / Uuendab osaleja infot", description = "Updates a participant info / Uuendab osaleja infot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden deletion. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PutMapping(path = "/{participantId}", produces = { "application/json" })
    public ParticipantDto updateParticipant(@PathVariable Long participantId, @RequestBody UpdateParticipantDto updateParticipantDto) {
        return this.manager.updateParticipant(participantId, updateParticipantDto);
    }

    @Operation(summary = "Return all participants regitered to an event by id / Tagastab kõik osalejad regiteeritud üritusele läbi ürituse id", description = "Return all participants regitered to an event by id / Tagastab kõik osalejad regiteeritud üritusele läbi ürituse id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ParticipantDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping(produces = { "application/json" })
    public Set<ParticipantDto> findAllParticipants() {
        return this.manager.findAllParticipants();
    }

}
