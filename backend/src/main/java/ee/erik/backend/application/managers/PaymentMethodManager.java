package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentMethodManager {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodManager(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    public Set<PaymentMethodDto> findAll() {
        return this.paymentMethodService.findAll().stream().map(Converters::convertPaymentMethodToPaymentMethodDto).collect(Collectors.toSet());
    }

    public PaymentMethodDto createPaymentMethod(CreatePaymentMethodDto createPaymentMethodDto) {
        return Converters.convertPaymentMethodToPaymentMethodDto(this.paymentMethodService.save(Converters.convertCreatePaymentMethodDtoToPaymentMethod(createPaymentMethodDto)));
    }

    public void deletePaymentMethod(Long paymentMethodId) {
        this.paymentMethodService.delete(paymentMethodId);
    }
}
