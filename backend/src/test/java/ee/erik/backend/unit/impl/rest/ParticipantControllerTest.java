package ee.erik.backend.unit.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.erik.backend.application.dto.read.CitizenDto;
import ee.erik.backend.application.dto.read.ParticipantDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdateBusinessDto;
import ee.erik.backend.application.dto.update.UpdateCitizenDto;
import ee.erik.backend.application.dto.update.UpdateParticipantDto;
import ee.erik.backend.application.managers.EventManager;
import ee.erik.backend.impl.rest.EventController;
import ee.erik.backend.impl.rest.ParticipantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ParticipantController.class)
public class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventManager eventManager;


    @Test
    public void controllerShouldUpdateParticipantInEvent() throws Exception {
        ParticipantDto participant = new ParticipantDto(
                1L,
                new PaymentMethodDto(1L, "sularaha"),
                "name",
                new CitizenDto(1L, "test" , 1232323L, "test"),
                null
        );
        UpdateParticipantDto createParticipantDto = new UpdateParticipantDto(
                new PaymentMethodDto(participant.getPaymentMethod().getId(), null),
                participant.getName(),
                participant.getCitizen() == null ? null : new UpdateCitizenDto(),
                participant.getBusiness() == null ? null : new UpdateBusinessDto()
        );
        given(this.eventManager.updateParticipant(1L, createParticipantDto)).willReturn(participant);

        MvcResult result = mockMvc.perform(put("/api/v1/participants/1")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(createParticipantDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        ParticipantDto res = new ObjectMapper().readValue(json, ParticipantDto.class);

        assertEquals(participant, res);
    }
}
