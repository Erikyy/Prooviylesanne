package ee.erik.core.infrastructure.persistance.mappers;

import ee.erik.core.domain.entities.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setName(rs.getString("name"));
        event.setDate(rs.getDate("date"));
        event.setLocation(rs.getString("location"));
        event.setInfo(rs.getString("info"));
        return event;
    }
}
