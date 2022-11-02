package ee.erik.backend.infrastructure.persistance.entities;

import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.infrastructure.persistance.entities.participant.BusinessEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.CitizenEntity;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("participant")
public class ParticipantEntity {
    @Id
    @Column("p_id")
    private Long id;

    @Column("p_payment_method_id")
    private AggregateReference<PaymentMethodEntity, Long> paymentMethod;

    @Column("p_name")
    private String name;

    @Column("c_participant_id")
    private CitizenEntity citizenEntity;

    @Column("b_participant_id")
    private BusinessEntity businessEntity;

    public Participant toParticipant() {
        return new Participant(this.id, new PaymentMethod(this.paymentMethod.getId(), ""), this.name, this.citizenEntity == null ? null : this.citizenEntity.toCitizen(), this.businessEntity == null ? null : this.businessEntity.toBusiness());
    }

    public static ParticipantEntity toEntity(Participant participant) {

        return new ParticipantEntity(participant.getId(), AggregateReference.to(participant.getPaymentMethod().getId()), participant.getName(),  CitizenEntity.toEntiy(participant.getCitizen()), BusinessEntity.toEntity(participant.getBusiness()));
    }
}
