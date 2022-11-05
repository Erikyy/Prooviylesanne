package ee.erik.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.erik.backend.application.dto.create.CreateEventDto;
import ee.erik.backend.application.dto.create.CreateParticipantDto;
import ee.erik.backend.application.dto.read.EventDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdateCitizenDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.application.managers.PaymentMethodManager;
import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.repositories.EventRepository;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import ee.erik.backend.domain.services.EventService;
import ee.erik.backend.domain.services.PaymentMethodService;
import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.CitizenEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbEventRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
import ee.erik.backend.infrastructure.persistance.repositories.DbPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class EverythingIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");


    @DynamicPropertySource
    static void registerDb(DynamicPropertyRegistry registry) {
        //here is a problem with setting these properties
        System.out.println(container.getJdbcUrl());
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        //registry.add("spring.datasource.driver-class-name", () -> "org.testcontainers.jdbc.ContainerDatabaseDriver");
    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;


    @Autowired
    private DbEventRepository dbEventRepository;

    @Autowired
    private DbParticipantRepository dbParticipantRepository;

    @Autowired
    private DbPaymentMethodRepository dbPaymentMethodRepository;

    @Autowired
    private PaymentMethodManager paymentMethodManager;

    @Autowired
    private PaymentMethodService paymentMethodService;


    @Test
    public void shouldCreateEventWithParticipantsAndPaymentMethod() throws Exception {

        Date date = Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC));

        PaymentMethodEntity paymentMethod = new PaymentMethodEntity();
        paymentMethod.setMethod("test");
        this.dbPaymentMethodRepository.save(paymentMethod);

        assertThat(this.paymentMethodManager.findAll()).isNotEmpty();

        EventEntity eventEntity = new EventEntity();
        eventEntity.setDate(date);
        eventEntity.setName("EventName");
        eventEntity.setLocation("Location");
        eventEntity.setInfo("Blah blah blah");

        EventEntity savedEventEntity = this.dbEventRepository.save(eventEntity);

        assertThat(this.eventRepository.findAllAfterDate(new Date())).isNotEmpty();

        CitizenEntity citizenEntity = new CitizenEntity();
        citizenEntity.setInfo("info");
        citizenEntity.setIdNumber(32432434535L);
        citizenEntity.setLastName("test");

        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setName("test");
        participantEntity.setPaymentMethod(AggregateReference.to(paymentMethod.getId()));
        participantEntity.setCitizenEntity(
                citizenEntity
        );


        savedEventEntity.setParticipantEntities(Set.of(participantEntity));
        EventEntity newSavedEventEntity = this.dbEventRepository.save(savedEventEntity);

        assertThat(newSavedEventEntity.getParticipantEntities().stream().findFirst()).isPresent();

        //------------------------------------------------------------------

        assertThat(this.eventRepository.findAll()).isNotEmpty();
        assertThat(this.dbEventRepository.findAllSet()).isNotEmpty();

        assertThat(this.dbParticipantRepository.findAll()).isNotEmpty();
        assertThat(this.eventService.findEvents(null)).isNotEmpty();

        assertThat(this.eventManager.findEvents(null)).isNotEmpty();

        //------------------------------------------------------------------

        assertThat(this.eventRepository.findAll()).isNotEmpty();
        Optional<Event> event = this.eventRepository.findAll().stream().findFirst();
        assertThat(event).isPresent();

        assertThat(event.get().getParticipants().stream().findFirst()).isPresent();
        Optional<Participant> participant = this.participantRepository.findById(event.get().getParticipants().stream().findFirst().get().getId());
        assertThat(participant).isPresent();

        MvcResult participant_result = mockMvc.perform(get("/api/v1/events/{eventId}/participants/{participantId}", event.get().getId(), participant.get().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String participantJsonRes = participant_result.getResponse().getContentAsString();

        ParticipantDto participantDto = new ObjectMapper().readValue(participantJsonRes, ParticipantDto.class);

        assertThat(participant).isNotNull();
        assertThat(participantDto).isEqualTo(Converters.convertToParticipantDto(participant.get()));

        UpdateParticipantDto updateParticipant = new UpdateParticipantDto(
                participant.get().getPaymentMethod().getId(),
                participant.get().getName(),
                new UpdateCitizenDto(
                        participant.get().getCitizen().getLastName(),
                        participant.get().getCitizen().getIdNumber(),
                        participant.get().getCitizen().getInfo()
                ),
                null
        );

        updateParticipant.setName("New name");

        MvcResult participant_update_result = mockMvc.perform(put("/api/v1/events/{eventId}/participants/{participantId}", event.get().getId(), participantDto.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updateParticipant)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String participantJsonResUpdate = participant_update_result.getResponse().getContentAsString();

        ParticipantDto updated_participant = new ObjectMapper().readValue(participantJsonResUpdate, ParticipantDto.class);

        assertThat(participant).isNotNull();
        assertThat(participant.get().getName()).isNotEqualTo(updated_participant.getName());
    }

    @Test
    public void shouldCreateEventAndReturn() throws Exception {
        CreateEventDto eventDto = new CreateEventDto();
        eventDto.setDate(Date.from(LocalDate.now().plusDays(4).atStartOfDay().toInstant(ZoneOffset.UTC)));
        eventDto.setName("test");
        eventDto.setInfo("test");
        eventDto.setLocation("test");

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(eventDto)))
                .andExpect(status().isOk())
                .andDo(print());

        MvcResult result = mockMvc.perform(get("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        EventDto[] res = new ObjectMapper().readValue(json, EventDto[].class);
        assertThat(this.eventRepository.findAll()).isNotEmpty();
        assertThat(Set.of(res)).isEqualTo(this.eventRepository.findAll().stream().map(Converters::convertToEventDto).collect(Collectors.toSet()));
    }

    @Test
    public void shouldReturnPaymentMethods() throws Exception {
        MvcResult payment_methods_res = mockMvc.perform(get("/api/v1/payment_methods")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String resJson = payment_methods_res.getResponse().getContentAsString();

        PaymentMethodDto[] res_payment_methods = new ObjectMapper().readValue(resJson, PaymentMethodDto[].class);

        assertThat(this.paymentMethodService.findAll()).isNotEmpty();
        assertThat(this.paymentMethodService.findAll().stream().map(Converters::convertPaymentMethodToPaymentMethodDto).collect(Collectors.toSet())).isEqualTo(Set.of(res_payment_methods));
    }

    @Test
    public void shouldDeleteParticipantAndReturnEmpty() throws Exception {

        Optional<Event> event = this.eventRepository.findAll().stream().findFirst();
        assertThat(event).isPresent();

        Optional<Participant> participant = this.participantRepository.findById(1L);
        assertThat(participant).isPresent();

        mockMvc.perform(delete("/api/v1/events/{eventId}/participants/{participantId}", event.get().getId(), participant.get().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


        assertThat(this.dbParticipantRepository.findAll()).isEmpty();
    }
}
