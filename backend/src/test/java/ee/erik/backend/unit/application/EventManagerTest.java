package ee.erik.backend.unit.application;

import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.managers.EventManagerImpl;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.PaymentMethod;
import ee.erik.backend.domain.services.EventSelector;
import ee.erik.backend.domain.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Tests EventManagerImpl class, mainly conversions from dto to domain entity and back
 */
@ExtendWith(MockitoExtension.class)
public class EventManagerTest {


    @Mock
    private EventService eventService;

    @InjectMocks
    private EventManager eventManager = new EventManagerImpl();

    private Event testEvent;

    private Participant testParticipant;

    @BeforeEach
    public void setup() {

        testEvent = new Event();

        testEvent.setName("Test");

        testParticipant = new Participant();
        testParticipant.setId(1L);
        testParticipant.setPaymentMethod(new PaymentMethod(1L, null));
        testParticipant.setName("Test");
    }

    // all Event to EventDto conversions
    @Test
    public void managerShouldConvertToEventDto() {

        given(this.eventService.findEvents(EventSelector.Before)).willReturn(Set.of(this.testEvent));
        given(this.eventService.createNewEvent(this.testEvent)).willReturn(this.testEvent);
        given(this.eventService.getEventById(1L)).willReturn(this.testEvent);

        {
            Set<EventDto> returnedEvents = this.eventManager.findEvents(EventSelector.Before);
            assertThat(returnedEvents).contains(Converters.convertToEventDto(this.testEvent));
        }

        {
            CreateEventDto createEventDto = new CreateEventDto();
            createEventDto.setName("Test");
            EventDto eventDto = this.eventManager.createNewEvent(createEventDto);

            assertThat(eventDto.getName()).isEqualTo("Test");
        }

        {
            EventDto returnedEventDto = this.eventManager.getEventById(1L);
            assertThat(returnedEventDto.getName()).isEqualTo("Test");
        }
    }

    //test all ParticipantDto conversions
    @Test
    public void managerShouldConvertToParticipantDto() {

        {
            Participant newParticipant = new Participant();

            newParticipant.setPaymentMethod(new PaymentMethod(1L, null));
            newParticipant.setName("Test");

            given(this.eventService.addParticipantToEvent(1L, newParticipant)).willReturn(newParticipant);
            CreateParticipantDto createParticipantDto = new CreateParticipantDto();
            createParticipantDto.setName("Test");
            createParticipantDto.setPaymentMethodId(1L);
            ParticipantDto participantDto = this.eventManager.addParticipantToEvent(1L, createParticipantDto);

            assertThat(participantDto.getName()).isEqualTo("Test");
        }



        {
            given(this.eventService.updateParticipant(this.testParticipant)).willReturn(this.testParticipant);

            UpdateParticipantDto updateParticipantDto = new UpdateParticipantDto();
            updateParticipantDto.setName("Test");
            updateParticipantDto.setPaymentMethod(new PaymentMethodDto(1L, null));

            ParticipantDto participantDto = this.eventManager.updateParticipant(1L, updateParticipantDto);

            assertThat(participantDto.getName()).isEqualTo("Test");
        }

        {
            given(this.eventService.findParticipantInEventById(1L, 1L)).willReturn(this.testParticipant);

            ParticipantDto participantDto = this.eventManager.findParticipantInEventById(1L, 1L);

            assertThat(participantDto.getName()).isEqualTo(this.testParticipant.getName());
        }

        {
            given(this.eventService.findAllParticipantsInEvent(1L)).willReturn(Set.of(this.testParticipant));

            Set<ParticipantDto> participantDtoSet = this.eventManager.findAllParticipantsInEvent(1L);
            assertThat(participantDtoSet).contains(Converters.convertToParticipantDto(this.testParticipant));
        }
    }
}
