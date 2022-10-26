package ee.erik.core.domain.repositories.participant;

import ee.erik.core.domain.entities.participant.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository {
    int count();

    void save(Business business);

    void update(Business business);

    void deleteById(Long id);

    List<Business> findAll();

    Optional<Business> findById(Long id);
}
