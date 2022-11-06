package ee.erik.backend.unit.application;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.application.managers.PaymentMethodManager;
import ee.erik.backend.application.managers.PaymentMethodManagerImpl;
import ee.erik.backend.domain.entities.PaymentMethod;
import ee.erik.backend.domain.services.PaymentMethodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Tests PaymentMethodManagerImpl class
 */
@ExtendWith(MockitoExtension.class)
public class PaymentMethodManagerTest {

    @Mock
    private PaymentMethodService paymentMethodService;

    @InjectMocks
    private PaymentMethodManager paymentMethodManager = new PaymentMethodManagerImpl();

    private PaymentMethod testPaymentMethod;

    @BeforeEach
    public void setup() {
        testPaymentMethod = new PaymentMethod(1L, "Test");
    }

    @Test
    public void managerShouldConvertToPaymentMethodDto() {
        PaymentMethod newPaymentMethod = new PaymentMethod();
        newPaymentMethod.setMethod("Test 2");
        given(this.paymentMethodService.save(newPaymentMethod)).willReturn(newPaymentMethod);
        given(this.paymentMethodService.findAll()).willReturn(Set.of(this.testPaymentMethod));
        given(this.paymentMethodService.findById(1L)).willReturn(this.testPaymentMethod);
        given(this.paymentMethodService.update(this.testPaymentMethod)).willReturn(this.testPaymentMethod);


        {
            PaymentMethodDto paymentMethod = this.paymentMethodManager.createPaymentMethod(new CreatePaymentMethodDto("Test 2"));
            assertThat(paymentMethod.getMethod()).isEqualTo("Test 2");
        }

        {
            Set<PaymentMethodDto> paymentMethodDtoSet = this.paymentMethodManager.findAll();
            assertThat(paymentMethodDtoSet).contains(Converters.convertPaymentMethodToPaymentMethodDto(this.testPaymentMethod));
        }

        {
            PaymentMethodDto paymentMethodDto = this.paymentMethodManager.findById(1L);
            assertThat(paymentMethodDto).isEqualTo(Converters.convertPaymentMethodToPaymentMethodDto(this.testPaymentMethod));
        }

        {
            UpdatePaymentMethodDto updatePaymentMethodDto = new UpdatePaymentMethodDto("Test");
            PaymentMethodDto paymentMethodDto = this.paymentMethodManager.updatePaymentMethodDto(1L, updatePaymentMethodDto);

            assertThat(paymentMethodDto).isEqualTo(Converters.convertPaymentMethodToPaymentMethodDto(this.testPaymentMethod));
        }
    }
}
