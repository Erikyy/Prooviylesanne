package ee.erik.backend.application.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventDto {
    private Long id;
    private String name;
    private Date date;
    private String location;
    private String info;
}
