package ee.erik.backend.domain.repositories;

import ee.erik.backend.domain.entities.Participant;

import java.util.Optional;
import java.util.Set;

public interface ParticipantRepository {

    Set<Participant> findAllByEvent(Long eventId);

    Set<Participant> findAll();
    Optional<Participant> findById(Long id);

    Optional<Participant> findByIdInEventById(Long participantId, Long eventId);

    /**
     * Deletes participant reference to event, does not delete participant entity
     * @param participantId
     * @param eventId
     */
    void delete(Long participantId, Long eventId);

    Participant save(Participant participant);
}
