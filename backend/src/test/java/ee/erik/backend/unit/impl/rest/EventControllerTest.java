package ee.erik.backend.unit.impl.rest;


import ee.erik.backend.application.dto.create.CreateCitizenDto;
import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.CitizenDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdateBusinessDto;
import ee.erik.backend.application.dto.update.UpdateCitizenDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.domain.services.EventSelector;
import ee.erik.backend.impl.rest.EventController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventManager eventManager;

    private EventDto testEvent;

    private ParticipantDto participant;

    @BeforeEach
    public void setup() {
        this.participant = new ParticipantDto(
                1L,
                new PaymentMethodDto(1L, "sularaha"),
                "name",
                new CitizenDto(1L, "test" , 1232323L, "test"),
                null
        );

        this.testEvent = new EventDto(
                1L,
                "test",
                Date.from(LocalDate.now().plusDays(5).atStartOfDay().toInstant(ZoneOffset.UTC)),
                "test",
                "info"
                );
    }

    @Test
    public void controllerShouldReturnEvents() throws Exception {
        given(this.eventManager.findEvents(EventSelector.Before)).willReturn(Set.of(testEvent));

        MvcResult result = mockMvc.perform(get("/api/v1/events?event=Before")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        EventDto[] res = new ObjectMapper().readValue(json, EventDto[].class);

        assertEquals(res[0], this.testEvent);
    }

    @Test
    public void controllerShouldGetEventById() throws Exception {
        given(this.eventManager.getEventById(1L)).willReturn(this.testEvent);

        MvcResult result = mockMvc.perform(get("/api/v1/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        EventDto res = new ObjectMapper().readValue(json, EventDto.class);

        assertEquals(res, this.testEvent);
    }

    @Test
    public void controllerShouldCreateNewEvent() throws Exception {
        CreateEventDto eventDto = new CreateEventDto();
        eventDto.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));
        eventDto.setName("test");
        eventDto.setInfo("test");
        eventDto.setLocation("test");

        given(this.eventManager.createNewEvent(eventDto)).willReturn(this.testEvent);

        MvcResult result = mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(eventDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        EventDto res = new ObjectMapper().readValue(json, EventDto.class);

        assertEquals(res, this.testEvent);
    }

    @Test
    public void controllerShouldDeleteEvent() throws Exception {



        mockMvc.perform(delete("/api/v1/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(this.eventManager).deleteEvent(1L);

    }

    @Test
    public void controllerShouldFindAllParticipantsInEvent() throws Exception {
        given(this.eventManager.findAllParticipantsInEvent(1L)).willReturn(Set.of(this.participant));

        MvcResult result = mockMvc.perform(get("/api/v1/events/1/participants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ParticipantDto[] res = new ObjectMapper().readValue(json, ParticipantDto[].class);
        assertEquals(
                Set.of(this.participant), Set.of(res));
    }

    @Test
    public void controllerShouldFindParticipantInEventById() throws Exception {
        given(this.eventManager.findParticipantInEventById(1L, 1L)).willReturn(this.participant);

        MvcResult result = mockMvc.perform(get("/api/v1/events/1/participants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ParticipantDto res = new ObjectMapper().readValue(json, ParticipantDto.class);

        assertEquals(this.participant, res);
    }

    @Test
    public void controllerShouldAddParticipantToEvent() throws Exception {
        CreateCitizenDto createCitizenDto = new CreateCitizenDto();
        createCitizenDto.setInfo(this.participant.getCitizen().getInfo());
        createCitizenDto.setIdNumber(this.participant.getCitizen().getIdNumber());
        createCitizenDto.setLastName(this.participant.getCitizen().getLastName());
        CreateParticipantDto createParticipantDto = new CreateParticipantDto();
        createParticipantDto.setBusiness(null);
        createParticipantDto.setCitizen(createCitizenDto);
        createParticipantDto.setPaymentMethodId(1L);

        given(this.eventManager.addParticipantToEvent(1L, createParticipantDto)).willReturn(this.participant);

        MvcResult result = mockMvc.perform(post("/api/v1/events/1/participants")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(createParticipantDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ParticipantDto res = new ObjectMapper().readValue(json, ParticipantDto.class);

        assertEquals(this.participant, res);
    }



    @Test
    public void controllerShouldDeleteParticipantInEvent() throws Exception {
        mockMvc.perform(delete("/api/v1/events/1/participants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(this.eventManager).deleteParticipantFromEvent(1L, 1L);
    }
}
