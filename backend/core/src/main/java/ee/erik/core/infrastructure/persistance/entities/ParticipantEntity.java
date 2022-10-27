package ee.erik.core.infrastructure.persistance.entities;

import ee.erik.core.domain.entities.Participant;
import ee.erik.core.domain.entities.participant.Citizen;
import ee.erik.core.domain.entities.participant.PaymentMethod;
import ee.erik.core.infrastructure.persistance.entities.participant.BusinessEntity;
import ee.erik.core.infrastructure.persistance.entities.participant.CitizenEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("participantentity")
public class ParticipantEntity {
    @Id
    @Column("p_id")
    private Long id;

    @Column("p_payment_method")
    private PaymentMethod paymentMethod;

    @Column("p_name")
    private String name;

    @Column("c_participant_id")
    private CitizenEntity citizenEntity;

    @Column("b_participant_id")
    private BusinessEntity businessEntity;

    public Participant toParticipant() {
        return new Participant(this.id, this.paymentMethod, this.name, this.citizenEntity.toCitizen(), this.businessEntity.toBusiness());
    }

    public static ParticipantEntity toEntity(Participant participant) {

        return new ParticipantEntity(participant.getId(), participant.getPaymentMethod(), participant.getName(), CitizenEntity.toEntiy(participant.getCitizen()), BusinessEntity.toEntity(participant.getBusiness()));
    }
}
