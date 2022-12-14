package ee.erik.backend.infrastructure.persistance.entities;

import ee.erik.backend.domain.entities.Citizen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("citizen")
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
        if (this.id == null) {
            return null;
        }
        return new Citizen(this.id, this.lastName, this.idNumber, this.info);
    }

    public static CitizenEntity toEntity(Citizen citizen) {
        if (citizen == null) {
            return null;
        }
        return new CitizenEntity(citizen.getId(), citizen.getLastName(), citizen.getIdNumber(), citizen.getInfo());
    }
}
