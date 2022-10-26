package ee.erik.core.infrastructure.persistance.mappers.participant;

import ee.erik.core.domain.entities.participant.Citizen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CitizenMapper implements RowMapper<Citizen> {
    @Override
    public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {
        Citizen citizen = new Citizen();
        citizen.setId(rs.getLong("c_id"));
        citizen.setLastName(rs.getString("c_last_name"));
        citizen.setIdNumber(rs.getLong("c_id_number"));
        citizen.setInfo(rs.getString("c_info"));
        return citizen;
    }
}
