package ee.erik.backend.infrastructure.persistance.entities.participant;

import ee.erik.backend.domain.entities.Participant;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import ee.erik.backend.infrastructure.persistance.entities.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("payment_method")
public class PaymentMethodEntity {

    @Id
    @Column("p_m_id")
    private Long id;

    @Column("p_m_method")
    private String method;

    public PaymentMethod toPaymentMethod() {
        return new PaymentMethod(this.id, this.method);
    }

    public static PaymentMethodEntity toEntity(PaymentMethod paymentMethod) {
        return new PaymentMethodEntity(paymentMethod.getId(), paymentMethod.getMethod());
    }
}
