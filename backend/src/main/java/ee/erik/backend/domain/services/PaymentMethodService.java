package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public Set<PaymentMethod> findAll() {
        return this.paymentMethodRepository.findAll();
    }

    public PaymentMethod save(PaymentMethod paymentMethod) {
        return this.paymentMethodRepository.save(paymentMethod);
    }

    public void delete(PaymentMethod paymentMethod) {
        this.paymentMethodRepository.delete(paymentMethod);
    }
}
