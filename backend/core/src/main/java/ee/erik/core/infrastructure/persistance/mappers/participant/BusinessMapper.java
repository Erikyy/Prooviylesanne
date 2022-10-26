package ee.erik.core.infrastructure.persistance.mappers.participant;

import ee.erik.core.domain.entities.participant.Business;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessMapper implements RowMapper<Business> {
    @Override
    public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
        Business business = new Business();
        business.setId(rs.getLong("b_id"));
        business.setRegCode(rs.getLong("b_reg_code"));
        business.setNumOfParticipants(rs.getLong("b_num_of_participants"));
        business.setInfo(rs.getString("b_info"));
        return business;
    }
}
