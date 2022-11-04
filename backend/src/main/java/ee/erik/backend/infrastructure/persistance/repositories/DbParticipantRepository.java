package ee.erik.backend.infrastructure.persistance.repositories;

import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface DbParticipantRepository extends CrudRepository<ParticipantEntity, Long> {

}
