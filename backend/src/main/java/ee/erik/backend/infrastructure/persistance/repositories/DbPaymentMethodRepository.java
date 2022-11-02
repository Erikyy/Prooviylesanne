package ee.erik.backend.infrastructure.persistance.repositories;

import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface DbPaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Long> {
    @Query("select * from payment_method")
    Set<PaymentMethodEntity> findAllSet();
}
