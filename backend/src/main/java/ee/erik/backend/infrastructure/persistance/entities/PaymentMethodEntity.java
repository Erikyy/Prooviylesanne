package ee.erik.backend.infrastructure.persistance.entities;

import ee.erik.backend.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
