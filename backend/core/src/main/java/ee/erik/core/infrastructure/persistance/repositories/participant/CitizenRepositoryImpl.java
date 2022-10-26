package ee.erik.core.infrastructure.persistance.repositories.participant;

import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.repositories.participant.CitizenRepository;
import ee.erik.core.infrastructure.persistance.mappers.participant.CitizenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CitizenRepositoryImpl implements CitizenRepository {
    private JdbcTemplate db;

    @Autowired
    public void setDb(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public int count() {
        var count = this.db.queryForObject("select count(*) from citizen", Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public void save(Citizen citizen) {
        this.db.update("insert into citizen(c_last_name, c_id_number, c_info) values (?, ?, ?)", citizen.getLastName(), citizen.getIdNumber(), citizen.getInfo());
    }

    @Override
    public void update(Citizen citizen) {
        this.db.update("update citizen set c_last_name = ?, c_id_number = ?, c_info = ? where c_id = ?", citizen.getLastName(), citizen.getIdNumber(), citizen.getInfo(), citizen.getId());
    }

    @Override
    public void deleteById(Long id) {
        this.db.update("delete from citizen where c_id = ?", id);
    }

    @Override
    public List<Citizen> findAll() {
        return this.db.query("select * from citizen", new CitizenMapper());
    }

    @Override
    public Optional<Citizen> findById(Long id) {
        return Optional.ofNullable(
                this.db.queryForObject("select * from citizen where c_id = ?", new CitizenMapper(), id)
        );
    }
}
