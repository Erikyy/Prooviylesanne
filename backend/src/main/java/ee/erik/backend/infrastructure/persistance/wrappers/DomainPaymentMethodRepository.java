package ee.erik.backend.infrastructure.persistance.wrappers;

import ee.erik.backend.domain.entities.PaymentMethod;
import ee.erik.backend.domain.repositories.PaymentMethodRepository;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DomainPaymentMethodRepository implements PaymentMethodRepository {

    private final DbPaymentMethodRepository dbPaymentMethodRepository;

    @Autowired
    public DomainPaymentMethodRepository(DbPaymentMethodRepository dbPaymentMethodRepository) {
        this.dbPaymentMethodRepository = dbPaymentMethodRepository;
    }

    @Override
    public Set<PaymentMethod> findAll() {
        return this.dbPaymentMethodRepository.findAllSet().stream().map(PaymentMethodEntity::toPaymentMethod).collect(Collectors.toSet());
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return this.dbPaymentMethodRepository.save(PaymentMethodEntity.toEntity(paymentMethod)).toPaymentMethod();
    }

    @Override
    public void delete(PaymentMethod paymentMethod) {
        this.dbPaymentMethodRepository.delete(PaymentMethodEntity.toEntity(paymentMethod));
    }

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        Optional<PaymentMethodEntity> paymentMethodEntity = this.dbPaymentMethodRepository.findById(id);
        return paymentMethodEntity.map(PaymentMethodEntity::toPaymentMethod);
    }
}
