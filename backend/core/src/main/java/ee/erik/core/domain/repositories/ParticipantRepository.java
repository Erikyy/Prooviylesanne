package ee.erik.core.domain.repositories;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository {
    int count();

    Participant save(Participant participant);

    Participant update(Participant participant);

    void deleteById(Long id);

    List<Participant> findAll();

    Optional<Participant> findById(Long id);
}
