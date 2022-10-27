package ee.erik.core.infrastructure.persistance.entities;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("evententity")
public class EventEntity {
    @Id
    @Column("e_id")
    private Long id;

    @Column("e_name")
    private String name;

    @Column("e_date")
    private Date date;

    @Column("e_location")
    private String location;

    @Column("e_info")
    private String info;

    @MappedCollection(keyColumn = "p_event_id", idColumn = "p_event_id")
    private Set<ParticipantEntity> participantEntities = new HashSet<>();

    public Event toEvent() {
        Set<Participant> participants = this.participantEntities.stream().map(ParticipantEntity::toParticipant)
                .collect(Collectors.toSet());

        return new Event(this.id, this.name, this.date, this.location, this.info, participants);
    }

    public static EventEntity toEntity(Event event) {
        Set<ParticipantEntity> participantEntitySet = event.getParticipants().stream().map(ParticipantEntity::toEntity)
                .collect(Collectors.toSet());
        return new EventEntity();
    }
}
