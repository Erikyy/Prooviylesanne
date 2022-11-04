package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;

import java.util.Optional;
import java.util.Set;

public interface ParticipantRepository {

    Optional<Participant> findById(Long id);

    void delete(Participant participant);

    Participant save(Participant participant);
}
