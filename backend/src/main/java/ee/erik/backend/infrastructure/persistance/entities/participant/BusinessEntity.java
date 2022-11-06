package ee.erik.backend.infrastructure.persistance.entities.participant;

import ee.erik.backend.domain.entities.Business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("business")
public class BusinessEntity {
    @Id
    @Column("b_id")
    private Long id;

    @Column("b_reg_code")
    private Long regCode;

    @Column("b_num_of_participants")
    private Long numOfParticipants;

    @Column("b_info")
    private String info;

    public Business toBusiness() {
        if(this.id == null) {
            return null;
        }
        return new Business(this.id, this.regCode, this.numOfParticipants, this.info);
    }

    public static BusinessEntity toEntity(Business business) {
        if (business == null) {
            return null;
        }
        return new BusinessEntity(business.getId(), business.getRegCode(), business.getNumOfParticipants(), business.getInfo());
    }
}
