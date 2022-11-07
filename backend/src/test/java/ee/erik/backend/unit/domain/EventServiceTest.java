package ee.erik.backend.unit.domain;


import ee.erik.backend.domain.entities.Citizen;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.domain.services.EventService;
import ee.erik.backend.domain.services.EventServiceImpl;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    @Captor
    private ArgumentCaptor<Event> eventArgumentCaptor;

    @Captor
    private ArgumentCaptor<Participant> participantArgumentCaptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void serviceShouldCreateNewEvent() {
        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        given(this.eventRepository.save(expectedEvent)).willReturn(expectedEvent);

        Event createdEvent = this.eventService.createNewEvent(expectedEvent);;

        verify(this.eventRepository).save(this.eventArgumentCaptor.capture());

        then(expectedEvent).isEqualTo(this.eventArgumentCaptor.getValue());
        then(createdEvent).isEqualTo(expectedEvent);
    }

    @Test
    public void serviceShouldAddExistingParticipantToEvent() {
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setCitizen(new Citizen());

        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));


        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));

        given(this.eventRepository.saveWithParticipant(expectedEvent, 1L)).willReturn(Optional.of(expectedEvent));

        assertDoesNotThrow(() -> {
            eventService.addParticipantToEvent(1L, participant);
        });

        verify(this.eventRepository).saveWithParticipant(expectedEvent, 1L);


    }

    @Test
    public void serviceShouldAddNewParticipantToEvent() {
        Participant participant = new Participant();
        participant.setCitizen(new Citizen());

        Participant savedParticipant = new Participant();
        savedParticipant.setId(1L);
        savedParticipant.setCitizen(new Citizen());

        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));


        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));

        given(this.participantRepository.save(participant)).willReturn(savedParticipant);
        given(this.eventRepository.saveWithParticipant(expectedEvent, 1L)).willReturn(Optional.of(expectedEvent));

        assertDoesNotThrow(() -> {
            eventService.addParticipantToEvent(1L, participant);
        });

        verify(this.eventRepository).saveWithParticipant(expectedEvent, 1L);
    }

    @Test
    public void serviceShouldUpdateParticipant() {
        Participant participant = new Participant();
        participant.setId(1L);

        given(this.participantRepository.findById(1L)).willReturn(Optional.of(participant));

        given(this.participantRepository.save(participant)).willReturn(participant);

        assertDoesNotThrow(() -> {
            this.eventService.updateParticipant(participant);
        });

        verify(this.participantRepository).save(this.participantArgumentCaptor.capture());

        then(participant).isEqualTo(this.participantArgumentCaptor.getValue());
    }

    @Test
    public void serviceShouldDeleteParticipantInEvent() {
        Participant participant = new Participant();
        participant.setId(1L);

        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));
        given(this.participantRepository.findByIdInEventById(1L, 1L)).willReturn(Optional.of(participant));

        assertDoesNotThrow(() -> {
            this.eventService.deleteParticipantFromEvent(1L, 1L);
        });

        verify(this.participantRepository, times(1)).delete(1L, 1L);
    }

    @Test
    public void serviceShouldDeleteEvent() {
        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));

        assertDoesNotThrow(() -> {
            this.eventService.deleteEvent(1L);
        });

        verify(this.eventRepository, times(1)).delete(this.eventArgumentCaptor.capture());

        then(expectedEvent).isEqualTo(this.eventArgumentCaptor.getValue());
    }

    @Test
    public void serviceShouldReturnAllParticipantsFromEvent() {
        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));

        assertDoesNotThrow(() -> {
            this.eventService.findAllParticipantsInEvent(1L);
        });
    }

    @Test
    public void serviceShouldNotThrowWhenGetEventById() {
        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        given(this.eventRepository.findById(1L)).willReturn(Optional.of(expectedEvent));

        assertDoesNotThrow(() -> {
            this.eventService.getEventById(1L);
        });

    }

    @Test
    public void serviceShouldNotThrowWhenFindParticipantByIdInEventById() {
        Participant participant = new Participant();
        participant.setId(1L);

        given(this.participantRepository.findByIdInEventById(1L, 1L)).willReturn(Optional.of(participant));

        assertDoesNotThrow(() -> {
            this.eventService.findParticipantInEventById(1L, 1L);
        });
    }

    @Test
    public void serviceShouldThrowWhenAddingOldDateToEvent() {
        Event expectedEvent = new Event();
        expectedEvent.setId(1L);
        expectedEvent.setDate(Date.from(LocalDate.now().minusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));

        assertThrows(DomainEventDateException.class, () -> {this.eventService.createNewEvent(expectedEvent);});
    }
}
