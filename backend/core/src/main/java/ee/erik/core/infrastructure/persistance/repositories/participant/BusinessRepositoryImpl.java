package ee.erik.core.infrastructure.persistance.repositories.participant;

import ee.erik.core.domain.entities.participant.Business;
import ee.erik.core.domain.repositories.participant.BusinessRepository;
import ee.erik.core.infrastructure.persistance.mappers.participant.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class BusinessRepositoryImpl implements BusinessRepository {
    private JdbcTemplate db;

    @Autowired
    public void setDb(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public int count() {
        var count = this.db.queryForObject("select count(*) from business", Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public void save(Business business) {
        this.db.update("insert into business(b_reg_code, b_num_of_participants, b_info) values (?, ?, ?)", business.getRegCode(), business.getNumOfParticipants(), business.getInfo());
    }

    @Override
    public void update(Business business) {
        this.db.update("update business set b_reg_code = ?, b_num_of_participants = ?, b_info = ? where b_id = ?", business.getRegCode(), business.getNumOfParticipants(), business.getInfo(), business.getId());
    }

    @Override
    public void deleteById(Long id) {
        this.db.update("delete from business where b_id = ?", id);
    }

    @Override
    public List<Business> findAll() {
        return this.db.query("select * from business", new BusinessMapper());
    }

    @Override
    public Optional<Business> findById(Long id) {
        return Optional.ofNullable(
                this.db.queryForObject("select * from business where b_id = ?", new BusinessMapper(), id)
        );
    }
}
