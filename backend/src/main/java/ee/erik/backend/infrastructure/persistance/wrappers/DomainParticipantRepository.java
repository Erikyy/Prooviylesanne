package ee.erik.backend.infrastructure.persistance.wrappers;

import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbParticipantRepository;
import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DomainParticipantRepository implements ParticipantRepository {

    private final DbParticipantRepository participantRepository;

    @Autowired
    public DomainParticipantRepository(DbParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Optional<Participant> findById(Long id) {
        Optional<ParticipantEntity> participantEntity = this.participantRepository.findById(id);
        return participantEntity.map(ParticipantEntity::toParticipant);
    }

    @Override
    public void delete(Participant participant) {
        this.participantRepository.delete(ParticipantEntity.toEntity(participant));
    }

    @Override
    public Participant save(Participant participant) {
        return this.participantRepository.save(ParticipantEntity.toEntity(participant)).toParticipant();
    }
}
