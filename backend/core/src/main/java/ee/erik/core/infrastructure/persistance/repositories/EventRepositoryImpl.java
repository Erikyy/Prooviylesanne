package ee.erik.core.infrastructure.persistance.repositories;

import ee.erik.core.domain.entities.Event;
import ee.erik.core.domain.repositories.EventRepository;
import ee.erik.core.infrastructure.persistance.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Event save(Event event) {
        this.db.update("insert into event(e_name, e_date, e_location, e_info) values (?, ?, ?, ?)", event.getName(), event.getDate(), event.getLocation(), event.getInfo());
        return event;
    }

    @Override
    public Event update(Event event) {
        this.db.update("update event set e_name = ?, e_date = ?, e_location = ?, e_info = ? where e_id = ?", event.getName(), event.getDate(), event.getLocation(), event.getInfo(), event.getId());
        return event;
    }

    @Override
    public void deleteById(Long id) {
        this.db.update("delete from event where e_id = ?", id);
    }

    @Override
    public List<Event> findAll() {
        return this.db.query("""
            select 
                e.e_id, 
                e.e_name, 
                e.e_date, 
                e.e_location, 
                e.e_info,
                p.p_id,
                p.name,
                p.p_payment_method
            from event e
            left join participant p on e.e_id = p.p_event_id
        """, new EventMapper());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(
          this.db.queryForObject("""
            select *
            from event e
            left join participant p on e.e_id = p.p_event_id
            where e.e_id = ?
        """, new EventMapper(), id)
        );
    }
}
