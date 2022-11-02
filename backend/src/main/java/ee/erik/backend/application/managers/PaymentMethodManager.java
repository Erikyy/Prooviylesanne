package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.CreatePaymentMethodDto;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PaymentMethodManager {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodManager(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    public Set<PaymentMethod> findAll() {
        return this.paymentMethodService.findAll();
    }

    public PaymentMethod createPaymentMethod(CreatePaymentMethodDto createPaymentMethodDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(createPaymentMethodDto.getMethod());
        return this.paymentMethodService.save(paymentMethod);
    }

    public void deletePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethodService.delete(paymentMethod);
    }
}
