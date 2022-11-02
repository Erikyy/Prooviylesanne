package ee.erik.backend.infrastructure.persistance.repositories;

import ee.erik.backend.infrastructure.persistance.entities.EventEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface DbEventRepository extends CrudRepository<EventEntity, Long> {

    //find all events that have already happened
    @Query("select * from event where e_date > :in_date")
    Set<EventEntity> findAllBeforeDate(@Param("in_date") Date date);

    //find future events
    @Query("select * from event where e_date <= :in_date")
    Set<EventEntity> findAllAfterDate(@Param("in_date") Date date);
}
