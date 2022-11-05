package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.domain.repositories.PaymentMethodRepository;
import ee.erik.backend.domain.services.exceptions.DomainNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public void delete(Long paymentMethodId) {
        Optional<PaymentMethod> paymentMethod = this.paymentMethodRepository.findById(paymentMethodId);
        if (paymentMethod.isPresent()) {
            this.paymentMethodRepository.delete(paymentMethod.get());
        } else {
            throw new DomainNotFoundException("Payment method not found: " + paymentMethodId);
        }

    }
}
