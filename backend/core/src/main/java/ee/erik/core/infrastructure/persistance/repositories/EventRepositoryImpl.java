package ee.erik.core.infrastructure.persistance.repositories;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.infrastructure.persistance.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private JdbcTemplate db;

    @Autowired
    public void setDb(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public int count() {
        var count = this.db.queryForObject("select count(*) from event", Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public void save(Event event) {
        this.db.execute("insert into event(name, date, location, info) values (?, ?, ?, ?)", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, event.getName());
                ps.setDate(2, new java.sql.Date(event.getDate().getTime()));
                ps.setString(3, event.getLocation());
                ps.setString(4, event.getInfo());
                return ps.execute();
            }
        });

    }

    @Override
    public void update(Event event) {
        this.db.update("update event set name = ?, date = ?, location = ?, info = ? where id = ?", event.getName(), event.getDate(), event.getLocation(), event.getInfo(), event.getId());
    }

    @Override
    public void deleteById(Long id) {
        this.db.update("delete from event where id = ?", id);
    }

    @Override
    public List<Event> findAll() {
        return this.db.query("select * from event", new EventMapper());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(
          this.db.queryForObject("select * from event where id = ?", new EventMapper(), id)
        );
    }
}
