package ee.erik.core.infrastructure.persistance.mappers;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.entities.Participant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    private ParticipantMapper participantMapper;

    public EventMapper() {
        this.participantMapper = new ParticipantMapper();
    }

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("e_id"));
        event.setName(rs.getString("e_name"));
        event.setDate(rs.getDate("e_date"));
        event.setLocation(rs.getString("e_location"));
        event.setInfo(rs.getString("e_info"));

        Participant participant = this.participantMapper.mapRow(rs, rowNum);
        if (participant != null) {
            event.getParticipants().add(participant);
        }

        return event;
    }
}
