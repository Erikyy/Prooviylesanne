package ee.erik.core.domain.repositories;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;

import java.util.Optional;
import java.util.Set;

public interface ParticipantRepository {

    Optional<Participant> findById(Long id);

    Set<Participant> findParticipantsByEvent(Event event);

    void delete(Participant participant);

    Participant save(Participant participant);
}
