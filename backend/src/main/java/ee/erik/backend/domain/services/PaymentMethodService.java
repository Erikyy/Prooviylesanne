package ee.erik.backend.domain.services;

import ee.erik.backend.domain.entities.PaymentMethod;

import java.util.Set;

public interface PaymentMethodService {
    public Set<PaymentMethod> findAll();
    PaymentMethod findById(Long id);
    PaymentMethod update(PaymentMethod paymentMethod);
    PaymentMethod save(PaymentMethod paymentMethod);
    void delete(Long id);
}
