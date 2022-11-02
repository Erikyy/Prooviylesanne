package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.participant.PaymentMethod;

import java.util.Optional;
import java.util.Set;

public interface PaymentMethodRepository {

    Set<PaymentMethod> findAll();

    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(PaymentMethod paymentMethod);
}
