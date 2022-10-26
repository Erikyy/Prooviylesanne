package ee.erik.core.infrastructure.persistance.repositories;

import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.repositories.ParticipantRepository;
import ee.erik.core.infrastructure.persistance.mappers.ParticipantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ParticipantRepositoryImpl implements ParticipantRepository {
    private JdbcTemplate db;

    @Autowired
    public void setDb(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public int count() {
        var count = this.db.queryForObject("select count(*) from participant", Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public Participant save(Participant participant) {
        Long citizen_id = null;
        Long business_id = null;

        if (participant.getCitizen() != null) {
            citizen_id = participant.getCitizen().getId();
        } else if(participant.getBusiness() != null) {
            business_id = participant.getBusiness().getId();
        }

        int rows = this.db.update("insert into participant(p_event_id, u_citizen_id, u_business_id, p_name, p_payment_method) values (?, ?, ?, ?, ?)", participant.getEvent().getId(), citizen_id, business_id, participant.getName(), participant.getPaymentMethod().value);
        if (rows != 0) {
            return participant;
        } else {
            return null;
        }
    }

    @Override
    public Participant update(Participant participant) {
        Long citizen_id = null;
        Long business_id = null;

        if (participant.getCitizen() != null) {
            citizen_id = participant.getCitizen().getId();
        } else if(participant.getBusiness() != null) {
            business_id = participant.getBusiness().getId();
        }

        int rows = this.db.update("update participant set p_event_id = ?, u_citizen_id = ?, u_business_id = ?, p_name = ?, p_payment_method = ? where p_id = ?", participant.getEvent().getId(), citizen_id, business_id, participant.getName(), participant.getPaymentMethod().value, participant.getId());
        if (rows != 0) {
            return participant;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.db.update("delete from participant where p_id = ?", id);
    }

    @Override
    public List<Participant> findAll() {
        return this.db.query("select * from participant", new ParticipantMapper());
    }

    @Override
    public Optional<Participant> findById(Long id) {
        return Optional.ofNullable(
                this.db.queryForObject("select * from participant where p_id = ?", new ParticipantMapper(), id)
        );
    }
}
