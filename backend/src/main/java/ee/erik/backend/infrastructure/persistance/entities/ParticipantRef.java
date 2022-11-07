package ee.erik.backend.infrastructure.persistance.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("event_participant")
public class ParticipantRef {

    @Id
    @Column("id")
    private Long id;

    AggregateReference<ParticipantEntity, Long> participantId;
}
