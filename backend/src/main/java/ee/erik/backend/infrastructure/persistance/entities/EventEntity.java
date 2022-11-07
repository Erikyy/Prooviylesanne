package ee.erik.backend.infrastructure.persistance.entities;

import ee.erik.backend.domain.entities.Event;
import ee.erik.backend.domain.entities.Participant;
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
@Table("event")
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

    @MappedCollection(keyColumn = "participant_id", idColumn = "event_id")
    private Set<ParticipantRef> participantEntities = new HashSet<>();

    public EventEntity(Long id, String name, Date date, String location, String info) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.info = info;
    }
    public Event toEvent() {
        return new Event(this.id, this.name, this.date, this.location, this.info);
    }

    public static EventEntity toEntity(Event event) {
        return new EventEntity(event.getId(), event.getName(), event.getDate(), event.getLocation(), event.getInfo());
    }
}
