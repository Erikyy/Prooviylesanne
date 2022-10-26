package ee.erik.core.infrastructure.persistance.mappers;

import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.entities.participant.Business;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import ee.erik.core.infrastructure.persistance.mappers.participant.BusinessMapper;
import ee.erik.core.infrastructure.persistance.mappers.participant.CitizenMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantMapper implements RowMapper<Participant> {

    private BusinessMapper businessMapper;
    private CitizenMapper citizenMapper;

    public ParticipantMapper() {
        this.businessMapper = new BusinessMapper();
        this.citizenMapper = new CitizenMapper();
    }

    @Override
    public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) {
            Participant participant = new Participant();
            participant.setName(rs.getString("p_name"));
            participant.setId(rs.getLong("p_id"));
            participant.setPaymentMethod(PaymentMethod.valueOf(rs.getString("p_payment_method")));

            Business business = this.businessMapper.mapRow(rs, rowNum);
            if (business != null) {
                participant.setBusiness(business);
            }

            Citizen citizen = this.citizenMapper.mapRow(rs, rowNum);
            if (citizen != null) {
                participant.setCitizen(citizen);
            }

            return participant;
        } else {
            return null;
        }

    }
}
