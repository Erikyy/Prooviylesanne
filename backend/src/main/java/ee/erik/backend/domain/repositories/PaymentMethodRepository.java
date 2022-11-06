package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.PaymentMethod;

import java.util.Optional;
import java.util.Set;

public interface PaymentMethodRepository {

    Set<PaymentMethod> findAll();

    Optional<PaymentMethod> findById(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(PaymentMethod paymentMethod);
}
