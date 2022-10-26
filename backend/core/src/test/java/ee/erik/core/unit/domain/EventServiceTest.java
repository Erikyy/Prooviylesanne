package ee.erik.core.unit.domain;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.domain.repositories.participant.BusinessRepository;
import ee.erik.core.domain.repositories.participant.CitizenRepository;
import ee.erik.core.domain.services.EventService;
import ee.erik.core.domain.services.exceptions.EventException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private CitizenRepository citizenRepository;

    @Captor
    private ArgumentCaptor<Event> eventArgumentCaptor;

    @Captor
    private ArgumentCaptor<Participant> participantArgumentCaptor;

    @InjectMocks
    private EventService eventService;

    private Event testEvent;
    private Participant testParticipant;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        testEvent = new Event();
        testEvent.setId(1L);
        testEvent.setInfo("Test");
        testEvent.setName("Test");
        testEvent.setLocation("Test");

        //always set future date otherwise this test will fail at some point
        testEvent.setDate(Date.from(LocalDate.now().plusMonths(2).atStartOfDay().toInstant(ZoneOffset.UTC)));

        Citizen citizen = new Citizen();
        citizen.setId(1L);
        citizen.setIdNumber(13847324240L);
        citizen.setLastName("Test");
        citizen.setInfo("Plah plah plah");

        testParticipant = new Participant();
        testParticipant.setId(1L);
        testParticipant.setName("Test");
        testParticipant.setEvent(testEvent);
        testParticipant.setCitizen(citizen);
        testParticipant.setPaymentMethod(PaymentMethod.BankTransfer);
    }

    @Test
    public void serviceShouldCreateNewEvent() {

        given(eventRepository.save(this.testEvent)).willReturn(this.testEvent);

        assertDoesNotThrow(() -> {
            eventService.createNewEvent(this.testEvent);
        });

        verify(eventRepository).save(eventArgumentCaptor.capture());
        Event returnedEvent = eventArgumentCaptor.getValue();

        then(this.testEvent).isEqualTo(returnedEvent);
    }

    @Test
    public void serviceShouldAddParticipantToEvent() {
        given(eventRepository.findById(1L)).willReturn(Optional.of(this.testEvent));


        assertDoesNotThrow(() -> {
            this.eventService.addParticipantToEvent(this.testParticipant, this.testEvent.getId());
        });

        verify(participantRepository).save(participantArgumentCaptor.capture());
        Participant participant = participantArgumentCaptor.getValue();

        then(this.testParticipant).isEqualTo(participant);
    }

    @Test
    public void serviceShouldUpdateParticipantInEvent() {
        given(eventRepository.findById(1L)).willReturn(Optional.of(this.testEvent));
        given(participantRepository.findById(1L)).willReturn(Optional.of(this.testParticipant));

        Citizen citizen = new Citizen();
        citizen.setId(1L);
        citizen.setIdNumber(13847324240L);
        citizen.setLastName("Test 33342fedfd");
        citizen.setInfo("Plah plah plah plah");

        Participant newTestParticipant = new Participant();
        newTestParticipant.setId(1L);
        newTestParticipant.setName("Test Test");
        newTestParticipant.setEvent(testEvent);
        newTestParticipant.setCitizen(citizen);
        newTestParticipant.setPaymentMethod(PaymentMethod.Cash);

        assertDoesNotThrow(() -> {
            this.eventService.updateParticipantInEvent(newTestParticipant, this.testEvent.getId());
        });

        verify(participantRepository).update(participantArgumentCaptor.capture());
        Participant capturedParticipant = participantArgumentCaptor.getValue();

        then(newTestParticipant).isEqualTo(capturedParticipant);
        then(this.testParticipant).isNotEqualTo(capturedParticipant);

    }

    @Test
    public void serviceShouldThrowExceptionOnAddedEventWhenDateHasPassed() {
        //set the date to something that has already passed, should throw exception
        this.testEvent.setDate(Date.from(LocalDate.now().minusMonths(2).atStartOfDay().toInstant(ZoneOffset.UTC)));
        assertThrows(EventException.class, () -> {
            eventService.createNewEvent(this.testEvent);
        });
    }

    @Test
    public void shouldRemoveParticipantAndReturn() {
        given(eventRepository.findById(1L)).willReturn(Optional.of(this.testEvent));
        given(participantRepository.findById(1L)).willReturn(Optional.of(this.testParticipant));

        assertDoesNotThrow(() -> {
            eventService.removeParticipantFromEvent(testEvent.getId(), testParticipant.getId());
        });

        verify(participantRepository).deleteById(1L);
    }

    @Test
    public void shouldDeleteEvent() {
        given(eventRepository.findById(1L)).willReturn(Optional.of(this.testEvent));

        assertDoesNotThrow(() -> {
            eventService.deleteEvent(1L);
        });

        verify(eventRepository).deleteById(1L);
    }
}
