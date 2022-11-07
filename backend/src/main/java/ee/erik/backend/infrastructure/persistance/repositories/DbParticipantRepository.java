package ee.erik.backend.infrastructure.persistance.repositories;

import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional
public interface DbParticipantRepository extends CrudRepository<ParticipantEntity, Long> {

    @Query("SELECT \"participant\".\"p_id\" AS \"p_id\", \"participant\".\"p_name\" AS \"p_name\", \"participant\".\"p_payment_method_id\" AS \"p_payment_method_id\", \"citizenEntity\".\"c_id\" AS \"citizenEntity_c_id\", \"citizenEntity\".\"c_info\" AS \"citizenEntity_c_info\", \"citizenEntity\".\"c_last_name\" AS \"citizenEntity_c_last_name\", \"citizenEntity\".\"c_id_number\" AS \"citizenEntity_c_id_number\", \"businessEntity\".\"b_id\" AS \"businessEntity_b_id\", \"businessEntity\".\"b_info\" AS \"businessEntity_b_info\", \"businessEntity\".\"b_reg_code\" AS \"businessEntity_b_reg_code\", \"businessEntity\".\"b_num_of_participants\" AS \"businessEntity_b_num_of_participants\" FROM \"participant\" LEFT OUTER JOIN \"citizen\" \"citizenEntity\" ON \"citizenEntity\".\"c_participant_id\" = \"participant\".\"p_id\" LEFT OUTER JOIN \"business\" \"businessEntity\" ON \"businessEntity\".\"b_participant_id\" = \"participant\".\"p_id\" JOIN event_participant ep ON participant.p_id = ep.participant_id WHERE ep.event_id = :id")
    Set<ParticipantEntity> findAllByEvent(@Param("id") Long id);

    @Query("SELECT \"participant\".\"p_id\" AS \"p_id\", \"participant\".\"p_name\" AS \"p_name\", \"participant\".\"p_payment_method_id\" AS \"p_payment_method_id\", \"citizenEntity\".\"c_id\" AS \"citizenEntity_c_id\", \"citizenEntity\".\"c_info\" AS \"citizenEntity_c_info\", \"citizenEntity\".\"c_last_name\" AS \"citizenEntity_c_last_name\", \"citizenEntity\".\"c_id_number\" AS \"citizenEntity_c_id_number\", \"businessEntity\".\"b_id\" AS \"businessEntity_b_id\", \"businessEntity\".\"b_info\" AS \"businessEntity_b_info\", \"businessEntity\".\"b_reg_code\" AS \"businessEntity_b_reg_code\", \"businessEntity\".\"b_num_of_participants\" AS \"businessEntity_b_num_of_participants\" FROM \"participant\" LEFT OUTER JOIN \"citizen\" \"citizenEntity\" ON \"citizenEntity\".\"c_participant_id\" = \"participant\".\"p_id\" LEFT OUTER JOIN \"business\" \"businessEntity\" ON \"businessEntity\".\"b_participant_id\" = \"participant\".\"p_id\" JOIN event_participant ep ON participant.p_id = ep.participant_id WHERE \"participant\".\"p_id\" = :participant_id AND ep.event_id = :event_id")
    Optional<ParticipantEntity> findByIdInEventById(@Param("participant_id") Long participantId, @Param("event_id") Long eventId);

    /**
     * Deletes reference of participant and event, does not delete participant as that participant
     * may be used later
     * @param participantId
     * @param eventId
     */
    @Modifying
    @Transactional
    @Query("delete from event_participant where event_id = :event_id and participant_id = :participant_id")
    void deleteByParticipantRef(@Param("participant_id") Long participantId, @Param("event_id") Long eventId);
}
