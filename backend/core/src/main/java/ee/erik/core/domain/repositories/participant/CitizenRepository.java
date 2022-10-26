package ee.erik.core.domain.repositories.participant;

import ee.erik.core.domain.entities.participant.Business;
import ee.erik.core.domain.entities.participant.Citizen;

import java.util.List;
import java.util.Optional;

public interface CitizenRepository {
    int count();

    void save(Citizen citizen);

    void update(Citizen citizen);

    void deleteById(Long id);

    List<Citizen> findAll();

    Optional<Citizen> findById(Long id);
}
