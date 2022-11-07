package ee.erik.backend.infrastructure.persistance.wrappers;

import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class DomainParticipantRepository implements ParticipantRepository {

    private final DbParticipantRepository participantRepository;

    @Autowired
    public DomainParticipantRepository(DbParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Set<Participant> findAllByEvent(Long eventId) {
        return this.participantRepository.findAllByEvent(eventId).stream().map(ParticipantEntity::toParticipant).collect(Collectors.toSet());
    }

    @Override
    public Set<Participant> findAll() {
        return StreamSupport.stream(this.participantRepository.findAll().spliterator(), false).map(ParticipantEntity::toParticipant).collect(Collectors.toSet());
    }

    @Override
    public Optional<Participant> findById(Long id) {
        Optional<ParticipantEntity> participantEntity = this.participantRepository.findById(id);
        return participantEntity.map(ParticipantEntity::toParticipant);
    }

    @Override
    public Optional<Participant> findByIdInEventById(Long participantId, Long eventId) {
        Optional<ParticipantEntity> participantEntity = this.participantRepository.findByIdInEventById(participantId, eventId);
        return participantEntity.map(ParticipantEntity::toParticipant);
    }

    @Override
    public void delete(Long participantId, Long eventId) {
        this.participantRepository.deleteByParticipantRef(participantId, eventId);
    }

    @Override
    public Participant save(Participant participant) {
        System.out.println(participant);
        return this.participantRepository.save(ParticipantEntity.toEntity(participant)).toParticipant();
    }
}
