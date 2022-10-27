package ee.erik.core.unit.domain;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.domain.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.MockitoAnnotations.openMocks;
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

    @InjectMocks
    private EventService eventService;

    @Captor
    private ArgumentCaptor<Event> eventArgumentCaptor;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void serviceShouldCreateNewEvent() {
        Event expectedEvent = new Event();

        given(this.eventRepository.save(expectedEvent)).willReturn(expectedEvent);

        Event createdEvent = this.eventService.createNewEvent(expectedEvent);

        verify(this.eventRepository).save(this.eventArgumentCaptor.capture());

        then(expectedEvent).isEqualTo(this.eventArgumentCaptor.getValue());
        then(createdEvent).isEqualTo(expectedEvent);
    }

    @Test
    public void serviceShouldAddParticipantToEvent() {

    }

    @Test
    public void serviceShouldUpdateParticipantInEvent() {

    }

    @Test
    public void serviceShouldDeleteEvent() {

    }
}
