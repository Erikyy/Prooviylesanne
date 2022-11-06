package ee.erik.backend.unit.impl.rest;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.ErrorDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;
import ee.erik.backend.application.managers.PaymentMethodManager;
import ee.erik.backend.impl.rest.PaymentMethodController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PaymentMethodController.class)
@AutoConfigureMockMvc
public class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentMethodManager paymentMethodManager;

    private PaymentMethodDto paymentMethod;

    @BeforeEach
    public void setup() {
        paymentMethod = new PaymentMethodDto(1L, "Test");
    }

    @Test
    public void controllerShouldFindAllPaymentMethods() throws Exception {
        given(this.paymentMethodManager.findAll()).willReturn(Set.of(this.paymentMethod));
        MvcResult result = mockMvc.perform(get("/api/v1/payment_methods")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        PaymentMethodDto[] res = new ObjectMapper().readValue(json, PaymentMethodDto[].class);

        assertThat(res[0]).isEqualTo(this.paymentMethod);
    }

    @Test
    public void controllerShouldFindPaymentMethodById() throws Exception {
        given(this.paymentMethodManager.findById(1L)).willReturn(this.paymentMethod);
        MvcResult result = mockMvc.perform(get("/api/v1/payment_methods/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        PaymentMethodDto res = new ObjectMapper().readValue(json, PaymentMethodDto.class);

        assertThat(res).isEqualTo(this.paymentMethod);
    }

    @Test
    public void controllerShouldCreatePaymentMethod() throws Exception {
        CreatePaymentMethodDto createPaymentMethodDto = new CreatePaymentMethodDto("Test");
        given(this.paymentMethodManager.createPaymentMethod(createPaymentMethodDto)).willReturn(this.paymentMethod);

        MvcResult result = mockMvc.perform(post("/api/v1/payment_methods")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(createPaymentMethodDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        PaymentMethodDto res = new ObjectMapper().readValue(json, PaymentMethodDto.class);

        assertThat(res).isEqualTo(this.paymentMethod);
    }

    @Test
    public void controllerShouldUpdatePaymentMethod() throws Exception {
        UpdatePaymentMethodDto updatePaymentMethodDto = new UpdatePaymentMethodDto("Test");

        given(this.paymentMethodManager.updatePaymentMethodDto(1L, updatePaymentMethodDto)).willReturn(this.paymentMethod);



        MvcResult result = mockMvc.perform(put("/api/v1/payment_methods/1")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatePaymentMethodDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        PaymentMethodDto res = new ObjectMapper().readValue(json, PaymentMethodDto.class);

        assertThat(res).isEqualTo(this.paymentMethod);
    }

    @Test
    public void controllerShouldDeletePaymentMethod() throws Exception {

        mockMvc.perform(delete("/api/v1/payment_methods/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(this.paymentMethodManager).deletePaymentMethod(1L);
    }
}
