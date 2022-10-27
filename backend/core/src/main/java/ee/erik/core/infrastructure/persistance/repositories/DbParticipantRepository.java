package ee.erik.core.infrastructure.persistance.repositories;

import ee.erik.core.infrastructure.persistance.entities.ParticipantEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DbParticipantRepository extends CrudRepository<ParticipantEntity, Long> {

    @Query("select * from participant where p_event_id = :id")
    Set<ParticipantEntity> findAllByEventId(@Param("id") Long id);
}
