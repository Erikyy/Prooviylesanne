package ee.erik.core.infrastructure.persistance.entities.participant;

import ee.erik.core.domain.entities.participant.Citizen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("citizenentity")
public class CitizenEntity {
    @Id
    @Column("c_id")
    private Long id;

    @Column("c_last_name")
    private String lastName;

    @Column("c_id_number")
    private Long idNumber;

    @Column("c_info")
    private String info;

    public Citizen toCitizen() {
        return new Citizen(this.id, this.lastName, this.idNumber, this.info);
    }

    public static CitizenEntity toEntiy(Citizen citizen) {
        return new CitizenEntity(citizen.getId(), citizen.getLastName(), citizen.getIdNumber(), citizen.getInfo());
    }
}
